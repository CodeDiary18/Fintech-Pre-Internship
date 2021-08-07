package com.example.finedu.service;

import com.example.finedu.dto.Observation;
import com.example.finedu.entity.FredData;
import com.example.finedu.repository.FredRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FredService {
    @Value("${fred.apikey}")
    String fredApiKey;

    final String baseUrl = "https://api.stlouisfed.org";
    // Thread-safe Values
    final public static ParameterizedTypeReference<Map<String, Object>> mapTypeReference = new ParameterizedTypeReference<>() {
    };
    final public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    final FredRepository fredRepository;

    @Autowired
    public FredService(FredRepository fredRepository) {
        this.fredRepository = fredRepository;
    }

    private Double safeParseDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (Throwable t) {
            return -999d;
        }
    }

    public Mono<Map<String, Object>> callUsbond10Y(String from, String to) {
        return WebClient.create(baseUrl)
                .method(HttpMethod.GET)
                .uri(it -> it.path("/fred/series/observations")
                        .queryParam("series_id", "DGS10")
                        .queryParam("units", "lin")
                        .queryParam("file_type", "json")
                        .queryParam("api_key", fredApiKey)
                        .queryParam("observation_start", "2020-12-01")
                        .queryParam("observation_end", "2020-12-31")
                        .build())
                .retrieve()
                .bodyToMono(mapTypeReference);
    }

    public Flux<Observation> getUsGovernmentBond10Y() {
        Flux<Observation> data = callUsbond10Y("2020-12-01", "2020-12-31")
                .flatMapMany(it -> {
                    List<Map<String, String>> list = (List<Map<String, String>>) it.get("observations");
                    return Flux.fromStream(
                            list.stream().map(m -> new Observation(m.get("date"), safeParseDouble(m.get("value"))))
                    );
                });
        return data;
    }

    @Scheduled(fixedDelay = 60000)
    public void updateFredDateRegularly() {
        log.info("Now updating freddata");
        String from = formatter.format(LocalDateTime.now().minusMonths(3));
        String to = formatter.format(LocalDateTime.now());
        Map<String, Object> data = (Map<String, Object>) callUsbond10Y(from, to).block(Duration.ofMinutes(5));
        List<Map<String, String>> list = (List<Map<String, String>>) data.get("observations");
        fredRepository.saveAll(list.stream()
                .map(m -> new FredData("DGS10", m.get("date"), safeParseDouble(m.get("value"))))
                .collect(Collectors.toList()));
    }

    public List<Observation> getStoredFredData(String seriesId, String from, String to) {
        return fredRepository.findAllBySeriesIdAndObservationDateAfterAndObservationDateBefore(seriesId, from, to)
                .stream().map(Observation::fromFredData).collect(Collectors.toList());
    }
}
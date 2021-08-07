package com.example.finedu.service;

import com.example.finedu.dto.Observation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class FredService {
    final String baseUrl = "https://api.stlouisfed.org";

    @Value("${fred.apikey}")
    String fredApiKey;

    private Double safeParseDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (Throwable t) {
            return -999d;
        }
    }

    public Flux<Observation> getUsGovernmentBond10Y(){
        // JDBC
        // DB에서 데이터를 가져와서 스트림으로 반환 (Blocking)
        // R2JDBC
        // FLUX
        Flux<Observation> data = WebClient.create(baseUrl)
                .method(HttpMethod.GET)
                .uri(it->it.path("/fred/series/observations")
                        .queryParam("series_id","DGS10")
                        .queryParam("units","lin")
                        .queryParam("file_type","json")
                        .queryParam("api_key",fredApiKey)
                        .queryParam("observation_start","2020-12-01")
                        .queryParam("observation_end","2020-12-31")
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .flatMapMany(it->{
                    List<Map<String,String>> list = (List<Map<String,String>>) it.get("observations");
                    return Flux.fromStream(
                            list.stream().map(m->new Observation(m.get("date"),safeParseDouble(m.get("value"))))
                    );
                });
        return data;
    }

}

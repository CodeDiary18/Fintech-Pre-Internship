package com.example.finedu.controller;

import com.example.finedu.dto.Observation;
import com.example.finedu.service.FredService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/fred")
@RequiredArgsConstructor
@Slf4j
public class FredController {
    final FredService fredService;

    @GetMapping(path="/usbond10y")
    public Flux<Observation> getUsBond10Y(){
        return fredService.getUsGovernmentBond10Y();
    }

    @GetMapping(path="/usbond10y_stream",produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Observation> getUsBond10YStream(){
        return Flux.interval(Duration.ofMillis(1000))
                .zipWith(fredService.getUsGovernmentBond10Y())  //스트림 두개를 합침
                .map(t -> t.getT2());       //합친 스트림 튜플에서 (interval값, Observation객체) 두번째 것 취사선택하는 mapper
    }

    @GetMapping(path = "/interval", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Long> interval(){
        return Flux.interval(Duration.ofMillis(300));
    }
}

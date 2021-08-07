package com.example.finedu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/fred")
@Slf4j
public class FredController {

    @GetMapping(path = "/interval", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Long> interval(){
        return Flux.interval(Duration.ofMillis(300));
    }
}

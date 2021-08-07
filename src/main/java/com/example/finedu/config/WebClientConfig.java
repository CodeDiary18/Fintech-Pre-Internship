package com.example.finedu.config;

import org.springframework.boot.web.codec.CodecCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class WebClientConfig implements WebFluxConfigurer {
    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        configurer.defaultCodecs().maxInMemorySize(-1);
    }

    @Bean
    public CodecCustomizer maxInMemorySizeCodeCustomizer() {
        return (configurer) -> configurer.defaultCodecs().maxInMemorySize(-1);
    }
}

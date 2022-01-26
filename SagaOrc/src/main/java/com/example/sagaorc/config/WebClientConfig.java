package com.example.sagaorc.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @Qualifier("payment")
    public WebClient paymentClient(@Value("${service.endpoints.payment}") String endpoint){
        return WebClient.builder()
                .baseUrl(endpoint)
                .build();
    }

    @Bean
    @Qualifier("resource")
    public WebClient resourceClient(@Value("${service.endpoints.resource}") String endpoint){
        return WebClient.builder()
                .baseUrl(endpoint)
                .build();
    }

}

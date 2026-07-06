package com.itcube.journal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class GlobalConfig {

    @Value("${keycloak.url}")
    private String keycloakUrl;

    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        return builder.baseUrl(keycloakUrl).build();
    }
}

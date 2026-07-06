package com.itcube.journal.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "keycloak")
public class KeycloakProperties {

    private String url;
    private String realm;
    private String clientId;
    private String clientSecret;
}

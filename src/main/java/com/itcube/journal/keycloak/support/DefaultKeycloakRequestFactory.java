package com.itcube.journal.keycloak.support;

import com.itcube.journal.config.KeycloakProperties;
import com.itcube.journal.dto.auth.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultKeycloakRequestFactory implements KeycloakRequestFactory {

    @Value("${external.keycloak.token-endpoint}")
    private String keycloakTokenEndpoint;

    @Value("${external.keycloak.revoke-endpoint}")
    private String keycloakRevokeEndpoint;

    @Value("${external.keycloak.introspect-endpoint}")
    private String keycloakIntrospectEndpoint;

    private final KeycloakProperties keycloakProperties;

    @Override
    public MultiValueMap<String, String> buildPasswordGrantForm(LoginRequestDto loginRequestDto) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();

        form.add("grant_type", "password");
        form.add("username", loginRequestDto.username());
        form.add("password", loginRequestDto.password());
        form.add("scope", loginRequestDto.scope());
        form.add("client_id", keycloakProperties.getClientId());
        form.add("client_secret", keycloakProperties.getClientSecret());

        return form;
    }

    @Override
    public MultiValueMap<String, String> buildRevokeForm(String refreshToken) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();

        form.add("token", refreshToken);
        form.add("token_type_hint", "refresh_token");
        form.add("client_id", keycloakProperties.getClientId());
        form.add("client_secret", keycloakProperties.getClientSecret());

        return form;
    }

    @Override
    public MultiValueMap<String, String> buildRefreshGrantForm(String refreshToken) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();

        form.add("grant_type", "refresh_token");
        form.add("refresh_token", refreshToken);
        form.add("client_id", keycloakProperties.getClientId());
        form.add("client_secret", keycloakProperties.getClientSecret());

        return form;
    }

    @Override
    public MultiValueMap<String, String> buildClientCredentialsForm() {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();

        form.add("grant_type", "client_credentials");
        form.add("client_id", keycloakProperties.getClientId());
        form.add("client_secret", keycloakProperties.getClientSecret());

        return form;
    }

    @Override
    public String tokenEndpoint() {
        return keycloakTokenEndpoint;
    }

    @Override
    public String introspectEndpoint() {
        return keycloakIntrospectEndpoint;
    }

    @Override
    public String revokeEndpoint() {
        return keycloakRevokeEndpoint;
    }

}

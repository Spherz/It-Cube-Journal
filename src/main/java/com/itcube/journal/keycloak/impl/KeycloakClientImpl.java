package com.itcube.journal.keycloak.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcube.journal.dto.auth.LoginRequestDto;
import com.itcube.journal.dto.auth.TokenResponse;
import com.itcube.journal.keycloak.KeycloakClient;
import com.itcube.journal.keycloak.support.KeycloakRequestFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakClientImpl implements KeycloakClient {

    private final RestClient restClient;
    private final KeycloakRequestFactory keycloakRequestFactory;

    @Override
    public TokenResponse exchangeTokenByPassword(LoginRequestDto loginRequestDto) {

        MultiValueMap<String, String> formData = keycloakRequestFactory.buildPasswordGrantForm(loginRequestDto);

        ResponseEntity<TokenResponse> response = restClient.post()
                .uri(keycloakRequestFactory.tokenEndpoint())
                .body(formData)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .retrieve()
                .toEntity(TokenResponse.class);

        return response.getBody();
    }

    @Override
    public void revokeRefreshToken(String refreshToken) {
        MultiValueMap<String, String> formData = keycloakRequestFactory.buildRevokeForm(refreshToken);

        restClient.post()
                .uri(keycloakRequestFactory.revokeEndpoint())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(formData)
                .retrieve()
                .toBodilessEntity();
    }
}

package com.itcube.journal.keycloak.support;

import com.itcube.journal.dto.auth.LoginRequestDto;
import org.springframework.util.MultiValueMap;

public interface KeycloakRequestFactory {
    MultiValueMap<String, String> buildPasswordGrantForm(LoginRequestDto loginRequestDto);

    MultiValueMap<String, String> buildRevokeForm(String refreshToken);

    MultiValueMap<String, String> buildRefreshGrantForm(String refreshToken);

    MultiValueMap<String, String> buildClientCredentialsForm();

    String tokenEndpoint();

    String introspectEndpoint();

    String revokeEndpoint();
}

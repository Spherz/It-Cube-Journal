package com.itcube.journal.keycloak;

import com.itcube.journal.dto.auth.LoginRequestDto;
import com.itcube.journal.dto.auth.TokenResponse;

public interface KeycloakClient {
    TokenResponse exchangeTokenByPassword(LoginRequestDto loginRequestDto);

    void revokeRefreshToken(String refreshToken);
}

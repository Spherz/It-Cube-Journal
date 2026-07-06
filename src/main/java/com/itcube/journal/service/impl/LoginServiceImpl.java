package com.itcube.journal.service.impl;

import com.itcube.journal.dto.auth.LoginRequestDto;
import com.itcube.journal.dto.auth.MessageResponse;
import com.itcube.journal.dto.auth.TokenResponse;
import com.itcube.journal.keycloak.KeycloakClient;
import com.itcube.journal.service.LoginService;
import com.itcube.journal.service.TokenCookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final KeycloakClient keycloakClient;
    private final TokenCookieService tokenCookieService;

    @Override
    public MessageResponse login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        TokenResponse tokenResponse = keycloakClient.exchangeTokenByPassword(loginRequestDto);

        log.info("Logging in");

        tokenCookieService.setAuthCookies(
                response,
                tokenResponse.access_token(),
                tokenResponse.refresh_token()
        );

        return new MessageResponse("User successfully logged in");
    }

    @Override
    public MessageResponse logout(HttpServletRequest request, HttpServletResponse response) {
        Optional<String> refreshToken = tokenCookieService.readRefreshToken(request);

        log.info("Logging out");

        refreshToken.ifPresent(keycloakClient::revokeRefreshToken);

        tokenCookieService.clearAuthCookies(response);

        return new MessageResponse("User successfully logged out");
    }
}

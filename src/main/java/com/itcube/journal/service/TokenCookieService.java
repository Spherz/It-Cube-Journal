package com.itcube.journal.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

public interface TokenCookieService {

    void setAuthCookies(HttpServletResponse response, String accessToken, String refreshToken);

    void clearAuthCookies(HttpServletResponse response);

    Optional<String> readAccessToken(HttpServletRequest request);

    Optional<String> readRefreshToken(HttpServletRequest request);
}

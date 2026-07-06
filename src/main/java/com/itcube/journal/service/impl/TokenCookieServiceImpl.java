package com.itcube.journal.service.impl;

import com.itcube.journal.service.TokenCookieService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenCookieServiceImpl implements TokenCookieService {

    @Override
    public void setAuthCookies(HttpServletResponse response, String accessToken, String refreshToken) {
        ResponseCookie accessCookie = ResponseCookie
                .from("ACCESS_TOKEN", accessToken)
                .httpOnly(true)
                .sameSite("None")
                .secure(true)
                .path("/")
                .maxAge(Duration.ofHours(1))
                .build();

        ResponseCookie refreshCookie = ResponseCookie
                .from("REFRESH_TOKEN", refreshToken)
                .httpOnly(true)
                .sameSite("None")
                .secure(true)
                .path("/")
                .maxAge(Duration.ofHours(2))
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
    }

    @Override
    public void clearAuthCookies(HttpServletResponse response) {
        ResponseCookie expiredAccessCookie = ResponseCookie
                .from("ACCESS_TOKEN", "")
                .httpOnly(true)
                .sameSite("None")
                .secure(true)
                .path("/")
                .maxAge(0)
                .build();

        ResponseCookie expiredRefreshCookie = ResponseCookie
                .from("REFRESH_TOKEN", "")
                .httpOnly(true)
                .sameSite("None")
                .secure(true)
                .path("/")
                .maxAge(0)
                .build();

        response.setHeader(HttpHeaders.SET_COOKIE, expiredAccessCookie.toString());
        response.setHeader(HttpHeaders.SET_COOKIE, expiredRefreshCookie.toString());
    }

    @Override
    public Optional<String> readAccessToken(HttpServletRequest request) {
        if (request.getCookies() == null) return Optional.empty();

        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("ACCESS_TOKEN")) {
                return Optional.of(cookie.getValue());
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<String> readRefreshToken(HttpServletRequest request) {
        if (request.getCookies() == null) return Optional.empty();

        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("REFRESH_TOKEN")) {
                return Optional.of(cookie.getValue());
            }
        }

        return Optional.empty();
    }
}

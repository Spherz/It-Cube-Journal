package com.itcube.journal.dto.auth;

public record TokenResponse(String refresh_token, String access_token,
                            String token_type, int expires_in,
                            String scope) {
}

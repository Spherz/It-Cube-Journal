package com.itcube.journal.dto.auth;

public record LoginRequestDto(String username, String password,
                              String scope) {
}

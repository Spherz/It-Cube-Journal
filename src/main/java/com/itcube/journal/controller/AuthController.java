package com.itcube.journal.controller;

import com.itcube.journal.dto.auth.LoginRequestDto;
import com.itcube.journal.dto.auth.MessageResponse;
import com.itcube.journal.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/auth")
public class AuthController {

    private final LoginService loginService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<MessageResponse> login(LoginRequestDto dto,
                                                 HttpServletResponse response) {
        return ResponseEntity.ok(loginService.login(dto, response));
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<MessageResponse> logout(HttpServletRequest request,
                                                  HttpServletResponse response) {
        return ResponseEntity.ok(loginService.logout(request, response));
    }

    @PostMapping(value = "/refresh")
    public ResponseEntity<MessageResponse> refresh(HttpServletRequest request,
                                                   HttpServletResponse response) {
        return ResponseEntity.ok(loginService.refresh(request, response));
    }
}

package com.itcube.journal.service;

import com.itcube.journal.dto.auth.LoginRequestDto;
import com.itcube.journal.dto.auth.MessageResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LoginService {

    MessageResponse login(LoginRequestDto loginRequestDto, HttpServletResponse response);

    MessageResponse logout(HttpServletRequest request, HttpServletResponse response);

    MessageResponse refresh(HttpServletRequest request, HttpServletResponse response);
}

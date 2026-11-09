package com.demo.AuthApp.service;

import com.demo.AuthApp.dto.LoginRequestDTO;
import com.demo.AuthApp.dto.LoginResponseDTO;

public interface AuthService {

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}

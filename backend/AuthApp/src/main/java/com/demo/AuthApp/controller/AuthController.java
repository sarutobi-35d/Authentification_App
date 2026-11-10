package com.demo.AuthApp.controller;

import com.demo.AuthApp.dto.LoginRequestDTO;
import com.demo.AuthApp.dto.LoginResponseDTO;
import com.demo.AuthApp.dto.UserRequestDTO;
import com.demo.AuthApp.dto.UserResponseDTO;
import com.demo.AuthApp.service.AuthService;
import com.demo.AuthApp.service.UtilisateurService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UtilisateurService utilisateurService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRequestDTO userRequestDTO) {

        UserResponseDTO cree = utilisateurService.createdUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cree);

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO request) {
//
//        try {
//            return ResponseEntity.ok(authService.login(request));
//        } catch (AuthenticationException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body(Map.of("message", "Email ou mot de passe incorrect"));
//        }
//    }

}

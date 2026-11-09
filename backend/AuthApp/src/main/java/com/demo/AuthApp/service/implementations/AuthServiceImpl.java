package com.demo.AuthApp.service.implementations;

import com.demo.AuthApp.dto.LoginRequestDTO;
import com.demo.AuthApp.dto.LoginResponseDTO;
import com.demo.AuthApp.entity.Utilisateur;
import com.demo.AuthApp.repository.UtilisateurRepository;
import com.demo.AuthApp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UtilisateurRepository utilisateurRepository;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        String email = loginRequestDTO.getEmail().trim().toLowerCase();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, loginRequestDTO.getPassword()));

        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("Identifiants invalides"));

        return new LoginResponseDTO(
                utilisateur.getId(),
                utilisateur.getNom(),
                utilisateur.getPrenom(),
                utilisateur.getEmail()
        );
    }
}

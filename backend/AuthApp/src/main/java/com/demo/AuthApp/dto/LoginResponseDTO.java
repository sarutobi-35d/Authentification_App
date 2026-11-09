package com.demo.AuthApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseDTO {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
}

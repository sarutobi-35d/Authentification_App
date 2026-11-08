package com.demo.AuthApp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponseDTO {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private LocalDateTime accountCreatedOn;
}

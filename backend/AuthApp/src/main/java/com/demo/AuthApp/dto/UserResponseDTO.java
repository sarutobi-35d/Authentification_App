package com.demo.AuthApp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {

    private Long id;
    private String nom;
    private String prenom;
    private String accountCreatedOn;
}

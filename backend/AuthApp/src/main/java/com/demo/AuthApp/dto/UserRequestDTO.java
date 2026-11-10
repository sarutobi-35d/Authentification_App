package com.demo.AuthApp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {

    @NotNull(message = "Nom obligatoire")
    private String nom;

    @NotNull(message = "Prénom obligatoire")
    private String prenom;

    @Email
    @NotNull(message = "Email obligatoire")
    private String email;

    @NotNull(message = "Mot de passe obligatoire")
    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères")
    private String password;
}

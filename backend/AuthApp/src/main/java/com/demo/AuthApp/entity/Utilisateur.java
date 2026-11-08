package com.demo.AuthApp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String prenom;

//    @Column(nullable = false, unique = true)
    private String email;

    private String motDePasse;

    private LocalDateTime accountCreatedOn = LocalDateTime.now();
}

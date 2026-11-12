package com.demo.AuthApp.controller;

import com.demo.AuthApp.dto.UserRequestDTO;
import com.demo.AuthApp.dto.UserResponseDTO;
import com.demo.AuthApp.service.UtilisateurService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllsUser(){
        return ResponseEntity.ok(utilisateurService.getAllUsers());
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> moi(Authentication authentication) {
        return ResponseEntity.ok(utilisateurService.trouverParEmail(authentication.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> trouverParId(@PathVariable Long id){
        return ResponseEntity.ok(utilisateurService.trouverUser(id));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserRequestDTO userRequestDTO){

        UserResponseDTO dto = utilisateurService.createdUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id,
                                                  @RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.ok(utilisateurService.editUser(id, userRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id){
        utilisateurService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

package com.demo.AuthApp.service;

import com.demo.AuthApp.dto.UserRequestDTO;
import com.demo.AuthApp.dto.UserResponseDTO;

import java.util.List;

public interface UtilisateurService {

    UserResponseDTO trouverUser(Long id);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO createdUser(UserRequestDTO userRequestDTO);
    UserResponseDTO editUser(Long id, UserRequestDTO userRequestDTO);
    void deleteUser(Long id);

    //Endpoint qui répond : « qui est l’utilisateur de ce token ? » partie React
    UserResponseDTO trouverParEmail(String email);

}

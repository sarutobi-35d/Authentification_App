package com.demo.AuthApp.service.implementations;

import com.demo.AuthApp.dto.UserRequestDTO;
import com.demo.AuthApp.dto.UserResponseDTO;
import com.demo.AuthApp.entity.Utilisateur;
import com.demo.AuthApp.repository.UtilisateurRepository;
import com.demo.AuthApp.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO trouverUser(Long id) {
        return utilisateurRepository.findById(id)
                .map(this::enDTO)
                .orElseThrow(() -> new RuntimeException("Aucun utilisateur trouvé avec l'Id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers() {
        return utilisateurRepository.findAll()
                .stream()
                .map(this::enDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO createdUser(UserRequestDTO userRequestDTO) {

        Utilisateur userToSave = enEntity(userRequestDTO);
        Utilisateur savedUser = utilisateurRepository.save(userToSave);

        return enDTO(savedUser);
    }

    @Override
    public UserResponseDTO editUser(Long id, UserRequestDTO userRequestDTO) {

        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aucun utilisateur trouvé avec l'Id: " + id));

        utilisateur.setNom(userRequestDTO.getNom());
        utilisateur.setPrenom(userRequestDTO.getPrenom());
        utilisateur.setEmail(userRequestDTO.getEmail());

        Utilisateur updatedUser = utilisateurRepository.save(utilisateur);

        return enDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {

        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aucun utilisateur trouvé avec l'Id: " + id));

        utilisateurRepository.delete(utilisateur);
    }

    //MAPPING

    private Utilisateur enEntity(UserRequestDTO requestDTO){

        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setNom(requestDTO.getNom());
        utilisateur.setPrenom(requestDTO.getPrenom());
        utilisateur.setEmail(requestDTO.getEmail());
        utilisateur.setMotDePasse(requestDTO.getPassword());

        return utilisateur;
    }

    private UserResponseDTO enDTO(Utilisateur utilisateur){

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId(utilisateur.getId());
        userResponseDTO.setNom(utilisateur.getNom());
        userResponseDTO.setPrenom(utilisateur.getPrenom());
        userResponseDTO.setEmail(utilisateur.getEmail());
        userResponseDTO.setMdp(utilisateur.getMotDePasse());
        userResponseDTO.setAccountCreatedOn(utilisateur.getAccountCreatedOn());

        return userResponseDTO;
    }
}

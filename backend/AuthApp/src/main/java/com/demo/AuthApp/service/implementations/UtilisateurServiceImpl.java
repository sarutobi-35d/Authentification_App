package com.demo.AuthApp.service.implementations;

import com.demo.AuthApp.dto.UserRequestDTO;
import com.demo.AuthApp.dto.UserResponseDTO;
import com.demo.AuthApp.entity.Utilisateur;
import com.demo.AuthApp.exception.EmailAlreadyUsedException;
import com.demo.AuthApp.exception.ResourceNotFoundException;
import com.demo.AuthApp.repository.UtilisateurRepository;
import com.demo.AuthApp.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    private Utilisateur chercherParId(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Aucun utilisateur trouvé avec l'id : " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO trouverUser(Long id) {
        return enDTO(chercherParId(id));
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
    @Transactional
    public UserResponseDTO createdUser(UserRequestDTO userRequestDTO) {

        String email = userRequestDTO.getEmail().trim().toLowerCase();

        if (utilisateurRepository.existsByEmail(email)) {
            throw new EmailAlreadyUsedException(email);
        }

        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setNom(userRequestDTO.getNom());
        utilisateur.setPrenom(userRequestDTO.getPrenom());
        utilisateur.setEmail(email);
        utilisateur.setMotDePasse(passwordEncoder.encode(userRequestDTO.getPassword()));

        return enDTO(utilisateurRepository.save(utilisateur));
    }

    @Override
    public UserResponseDTO editUser(Long id, UserRequestDTO userRequestDTO) {

        Utilisateur utilisateur = chercherParId(id);

        String email = userRequestDTO.getEmail().trim().toLowerCase();

        if (utilisateurRepository.existsByEmailAndIdNot(email, id)) {
            throw new EmailAlreadyUsedException(email);
        }

        utilisateur.setNom(userRequestDTO.getNom());
        utilisateur.setPrenom(userRequestDTO.getPrenom());
        utilisateur.setEmail(email);

        Utilisateur updatedUser = utilisateurRepository.save(utilisateur);

        return enDTO(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {

        utilisateurRepository.delete(chercherParId(id));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO trouverParEmail(String email) {
        return utilisateurRepository.findByEmail(email.trim().toLowerCase())
                .map(this::enDTO)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Aucun utilisateur trouvé avec l'email : " + email));
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
        userResponseDTO.setAccountCreatedOn(utilisateur.getAccountCreatedOn());

        return userResponseDTO;
    }
}

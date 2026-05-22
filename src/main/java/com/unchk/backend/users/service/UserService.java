package com.unchk.backend.users.service;

import com.unchk.backend.users.dto.UserRequestDTO;
import com.unchk.backend.users.dto.UserResponseDTO;
import com.unchk.backend.users.entity.Role;
import com.unchk.backend.users.entity.User;
import com.unchk.backend.users.repository.RoleRepository;
import com.unchk.backend.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service responsable de la gestion des utilisateurs
 */
@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Création d'un utilisateur
     *
     * @param request données utlisateur
     * @return utilisateur créé
     */
    public UserResponseDTO createUser(UserRequestDTO request) {

        // Vérifie si le role existe
        Role role = roleRepository.findByName(request.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // Création uilisateur
        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        // Sauvegarde en base
        User savedUser = userRepository.save(user);

        // Retour DTO sécurisé
        return mapToResponse(savedUser);
    }

    /**
     * Retourne tous les utilisateurs
     *
     * @return liste utilisateurs
     */
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    /**
     * Conversion User -> UserResponseDTO
     *
     * @param user utilisateur
     * @return DTO sécurisé
     */

    private UserResponseDTO mapToResponse(User user) {

        return UserResponseDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole().getName())
                .build();
    }
}

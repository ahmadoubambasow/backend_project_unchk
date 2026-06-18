package com.unchk.backend.users.service;

import com.unchk.backend.users.dto.UpdateProfileRequestDTO;
import com.unchk.backend.users.dto.UserRequestDTO;
import com.unchk.backend.users.dto.UserResponseDTO;
import com.unchk.backend.users.entity.Role;
import com.unchk.backend.users.entity.User;
import com.unchk.backend.users.entity.UserRole;
import com.unchk.backend.users.repository.RoleRepository;
import com.unchk.backend.users.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * Création utilisateur
     */
    public UserResponseDTO createUser(
            UserRequestDTO request
    ) {

        Role role = roleRepository

                .findById(request.getRoleId())

                .orElseThrow(() ->
                        new RuntimeException(
                                "Role introuvable"
                        )
                );

        User user = User.builder()

                .fullName(
                        request.getFullName()
                )

                .email(
                        request.getEmail()
                )

                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )

                .role(
                        role
                )

                .build();

        User savedUser =
                userRepository.save(user);

        return mapToResponse(savedUser);
    }

    /**
     * Liste utilisateurs
     */
    public List<UserResponseDTO>
    getAllUsers() {

        return userRepository.findAll()

                .stream()

                .map(this::mapToResponse)

                .toList();
    }

    /**
     * Modification utilisateur
     */
    public UserResponseDTO updateUser(

            Long id,

            UserRequestDTO request
    ) {

        User user =

                userRepository.findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Utilisateur introuvable"
                                )
                        );

        Role role =

                roleRepository.findById(
                                request.getRoleId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Role introuvable"
                                )
                        );

        user.setFullName(
                request.getFullName()
        );

        user.setEmail(
                request.getEmail()
        );

        user.setRole(
                role
        );

        if (

                request.getPassword() != null

                        &&

                        !request.getPassword().isBlank()

        ) {

            user.setPassword(

                    passwordEncoder.encode(
                            request.getPassword()
                    )
            );
        }

        User updatedUser =

                userRepository.save(
                        user
                );

        return mapToResponse(
                updatedUser
        );
    }

    /**
     * Suppression utilisateur
     */
    public void deleteUser(
            Long id
    ) {

        User user =

                userRepository.findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Utilisateur introuvable"
                                )
                        );

        userRepository.delete(
                user
        );
    }

    /**
     * Profil connecté
     */
    public UserResponseDTO getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email =
                authentication.getName();

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Utilisateur introuvable"
                                )
                        );

        return mapToResponse(user);
    }

    /**
     * Modification du profil connecté
     */
    public UserResponseDTO updateCurrentUser(
            UpdateProfileRequestDTO request
    ) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email =
                authentication.getName();

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Utilisateur introuvable"
                                )
                        );

        user.setFullName(
                request.getFullName()
        );


        if (

                request.getPassword() != null

                        &&

                        !request.getPassword().isBlank()

        ) {

            user.setPassword(

                    passwordEncoder.encode(
                            request.getPassword()
                    )
            );
        }

        User updatedUser =
                userRepository.save(user);

        return mapToResponse(
                updatedUser
        );
    }

    /**
     * Liste des formateurs
     */
    public List<UserResponseDTO>
    getAllTrainers() {

        List<UserRole> trainerRoles = List.of(

                UserRole.ENSEIGNANT,
                UserRole.ENSEIGNANT_ASSOCIE,
                UserRole.TUTEUR
        );

        return userRepository

                .findByRole_NameIn(
                        trainerRoles
                )

                .stream()

                .map(this::mapToResponse)

                .toList();
    }

    /**
     * Mapping Entity -> DTO
     */
    private UserResponseDTO mapToResponse(
            User user
    ) {

        return UserResponseDTO

                .builder()

                .id(
                        user.getId()
                )

                .fullName(
                        user.getFullName()
                )

                .email(
                        user.getEmail()
                )

                .roleId(
                        user.getRole().getId()
                )

                .roleName(
                        user.getRole().getName().name()
                )

                .build();
    }
}
package com.unchk.backend.users.controller;

import com.unchk.backend.users.dto.UpdateProfileRequestDTO;
import com.unchk.backend.users.dto.UserRequestDTO;
import com.unchk.backend.users.dto.UserResponseDTO;
import com.unchk.backend.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controller REST de gestion des utilisateurs
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Création utilisateur
     *
     * @param request données utilisateur
     * @return utilisateur créé
     */
    @PreAuthorize(
            "hasRole('ADMIN')"
    )
    @PostMapping
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO request) {
        return userService.createUser(request);
    }

    /**
     * Liste utilisateurs
     *
     * @return liste utilisateurs
     */
    @PreAuthorize(
            "hasRole('ADMIN')"
    )
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Modification utilisateur
     * @param id
     * @param request
     * @return
     */
    @PreAuthorize(
            "hasRole('ADMIN')"
    )
    @PutMapping("/{id}")
    public UserResponseDTO updateUser(

            @PathVariable
            Long id,

            @Valid
            @RequestBody
            UserRequestDTO request
    ) {

        return userService.updateUser(
                id,
                request
        );
    }

    /**
     * Suppression utilisateur
     * @param id
     */

    @PreAuthorize(
            "hasRole('ADMIN')"
    )
    @DeleteMapping("/{id}")
    public void deleteUser(
            @PathVariable
            Long id
    ) {

        userService.deleteUser(
                id
        );
    }

    /**
     * Récupérer le profil de l'utilisateur courant
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public UserResponseDTO getCurrentUser() {

        return userService.getCurrentUser();
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/profile")
    public UserResponseDTO updateCurrentUser(
            @Valid
            @RequestBody UpdateProfileRequestDTO request
    ) {

        return userService.updateCurrentUser(
                request
        );
    }

    /**
     * Liste des formateurs
     * @return
     */
    @PreAuthorize(
            "hasAnyRole("
                + "'ADMIN',"
                + "'RESPONSABLE_FORMATION'"
                + ")"
    )
    @GetMapping("/trainers")
    public List<UserResponseDTO>
    getAllTrainers() {

        return userService
                .getAllTrainers();
    }
}

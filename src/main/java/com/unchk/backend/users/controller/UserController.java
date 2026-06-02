package com.unchk.backend.users.controller;

import com.unchk.backend.users.dto.UserRequestDTO;
import com.unchk.backend.users.dto.UserResponseDTO;
import com.unchk.backend.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    @PostMapping
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO request) {
        return userService.createUser(request);
    }

    /**
     * Liste utilisateurs
     *
     * @return liste utilisateurs
     */
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

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

    @DeleteMapping("/{id}")
    public void deleteUser(
            @PathVariable
            Long id
    ) {

        userService.deleteUser(
                id
        );
    }
}

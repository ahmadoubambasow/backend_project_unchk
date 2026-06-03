package com.unchk.backend.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * DTO utilisé pour créer un utilisateur
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserRequestDTO {

    /**
     * Nom complet utilisateur
     */
    @NotBlank(message = "Email invalide")
    private String fullName;

    /**
     * Email utilisateur
     */
    @Email(message = "Email invalide")
    @NotBlank(message = "l'email est obligatoire")
    private String email;

    /**
     * Mot de passe utilisateur
     */
    @NotBlank(message = "Le mot de passe est obligatoire")
    private String password;

    /**
     * Nom du role
     */
    @NotNull(message = "Le role est obligatoire")
    private Long roleId;
}

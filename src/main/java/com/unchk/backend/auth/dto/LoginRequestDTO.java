package com.unchk.backend.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * DTO de connexion utilisateur
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDTO {

    /**
     * Email utilisateur
     */
    @Email
    @NotBlank
    private String email;

    /**
     * Mot de passe utilisateur
     */
    @NotBlank
    private String password;
}

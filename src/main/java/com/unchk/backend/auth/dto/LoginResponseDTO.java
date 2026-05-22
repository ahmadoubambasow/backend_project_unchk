package com.unchk.backend.auth.dto;

import lombok.*;

/**
 * DTO retourné après authentification
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDTO {

    /**
     * JWT Token
     */
    private String token;

    /**
     * Nom utilisateur
     */
    private String fullName;

    /**
     * Email utilisateur
     */
    private String email;

    /**
     * Role utilisateur
     */
    private String role;
}

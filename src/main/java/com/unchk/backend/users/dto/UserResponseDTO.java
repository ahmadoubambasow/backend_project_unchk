package com.unchk.backend.users.dto;

import lombok.*;

/**
 * DTO retourné au frontend.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {

    /**
     * ID Utilisateur
     */
    private Long id;

    /**
     * Nom complet
     */
    private String fullName;

    /**
     * Email utilisateur
     */
    private String email;

    /**
     * Nom du role
     */
    private  Long roleId;

    private String roleName;
}

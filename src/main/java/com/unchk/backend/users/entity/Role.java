package com.unchk.backend.users.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entité représentant un role utilisateur
 * Exemple: ADMIN, ETUDIANT, ENSEIGNANT
 */

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    /**
     * Identifiant unique du role.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private UserRole name;
}

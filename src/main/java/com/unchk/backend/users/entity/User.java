package com.unchk.backend.users.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entitéreprésentant un utilisateur du système.
 */

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    /**
     * Identifiant unique utilisateur
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nom Complet uttilisateur
     */
    @Column(nullable = false)
    private String fullName;

    /**
     * Email unique utilisateur
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Mot de passe crypté
     */
    @Column(nullable = false)
    private String password;

    /**
     * Relation plusieurs utilisateurs -> un role
     */
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}

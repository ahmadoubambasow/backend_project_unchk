package com.unchk.backend.filieres.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "filieres")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Filiere {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    /**
     * Nom filière
     * Informatique
     * Gestion
     * Droit
     */
    @Column(nullable = false)
    private String name;

    /**
     * Code
     * INFO
     * GEST
     * DROIT
     */
    @Column(nullable = false, unique = true)
    private String code;

    /**
     * Description
     */
    private String description;
}
package com.unchk.backend.promotions.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "promotions")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Promotion {

    /**
     * ID
     */
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    /**
     * Nom promotion
     * Ex: Promotion 2025-2026
     */
    @Column(nullable = false)
    private String name;

    /**
     * Année académique
     */
    @Column(nullable = false)
    private String academicYear;

    /**
     * Nombre maximal
     * d'étudiants admis.
     */
    private Integer capacity;
}
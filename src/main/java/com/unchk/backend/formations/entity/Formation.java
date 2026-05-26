package com.unchk.backend.formations.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "formations")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Formation {

    /**
     * ID formation
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Code formation
     */
    @Column(nullable = false, unique = true)
    private String code;

    /**
     * Nom formation
     */
    @Column(nullable = false)
    private String name;

    /**
     * Description
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Durée ex: 06 mois
     */
    private Integer duration;

    /**
     * Statut formation
     */
    @Enumerated(EnumType.STRING)
    private  FormationStatus status;

    /**
     * Date création
     */
    private LocalDateTime createdAt;
}

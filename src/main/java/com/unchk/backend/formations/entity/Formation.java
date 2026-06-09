package com.unchk.backend.formations.entity;

import com.unchk.backend.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "formations")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Formation extends BaseEntity {

    /**
     * ID formation
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nom formation
     */
    @Column(nullable = false)
    private String name;

    /**
     * Date début
     */
    private LocalDate startDate;

    /**
     * Date fin
     */
    private LocalDate endDate;

    /**
     * Type formation
     */
    @Enumerated(EnumType.STRING)
    private FormationType formationType;

    /**
     * Niveau
     */
    @Enumerated(EnumType.STRING)
    private FormationLevel level;

    /**
     * Financement
     */
    private Double fundingAmount;

    /**
     * Type financement
     */
    private String fundingType;

    /**
     * Nombre hommes
     */
    private Integer maleCount;

    /**
     * Nombre femmes
     */
    private Integer femaleCount;

    /**
     * Description
     */
    @Column(columnDefinition = "TEXT")
    private String description;
}

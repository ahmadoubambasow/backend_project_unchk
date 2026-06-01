package com.unchk.backend.subjects.entity;

import com.unchk.backend.formations.entity.Formation;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subjects")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subject {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Code matière
     */
    @Column(nullable = false, unique = true)
    private String code;

    /**
     * Nom matière
     */
    @Column(nullable = false)
    private String name;

    /**
     * Description
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Coefficient
     */
    private Integer coefficient;

    /**
     * Volume horaire
     */
    private Integer hours;

    /**
     * Formation associée
     */
    @ManyToOne
    @JoinColumn(
            name = "formation_id",
            nullable = false
    )
    private Formation formation;

}

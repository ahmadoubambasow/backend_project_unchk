package com.unchk.backend.evaluations.entity;

import com.unchk.backend.subjects.entity.Subject;
import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "evaluations")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nom
     * CC1
     * CC2
     * TP
     * Examen Final
     */
    @Column(nullable = false)
    private String title;

    /**
     * Date évaluation
     */
    private LocalDate evaluationDate;

    /**
     * Note maximale
     */
    private Double maxScore;

    /**
     * Coefficient
     */
    private Double coefficient;

    /**
     * Matière concernée
     */
    @ManyToOne
    @JoinColumn(
            name = "subject_id",
            nullable = false
    )
    private Subject subject;

}

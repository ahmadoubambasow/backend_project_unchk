package com.unchk.backend.enrollments.entity;

import com.unchk.backend.formations.entity.Formation;
import com.unchk.backend.students.entity.Student;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enrollment {

    /**
     * ID inscription
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Etudiant
     */
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    /**
     * Formation.
     */
    @ManyToOne
    @JoinColumn(name = "formation_id")
    private Formation formation;

    /**
     * Date inscription.
     */
    private LocalDate enrollmentDate;

    /**
     * Année académique.
     */
    private String academicYear;

    /**
     * Statut inscription.
     */
    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    /**
     * Date création.
     */
    private LocalDateTime createdAt;
}

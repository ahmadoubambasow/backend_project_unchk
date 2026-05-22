package com.unchk.backend.students.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entité étudiant
 */

@Entity
@Table(name = "students")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    /**
     * ID étudiant
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Matricule étudiant
     */
    @Column(nullable = false, unique = true)
    private String matricule;

    /**
     * Prénom
     */
    @Column(nullable = false)
    private String firstName;

    /**
     * Nom
     */
    @Column(nullable = false)
    private String lastName;

    /**
     * Email
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Téléphone
     */
    private String phone;

    /**
     * Genre
     */
    @Enumerated(EnumType.STRING)
    private Gender gender;

    /**
     * Date naissance.
     */
    private LocalDate birthDate;

    /**
     * Adresse.
     */
    private String address;

    /**
     * Statut étudiant.
     */
    @Enumerated(EnumType.STRING)
    private StudentStatus status;

    /**
     * Date création.
     */
    private LocalDateTime createdAt;
}

package com.unchk.backend.students.entity;

import com.unchk.backend.formations.entity.Formation;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "students")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            unique = true,
            nullable = false
    )
    private String ine;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String promotion;

    private Integer startYear;

    private Integer graduationYear;

    @Column(length = 3000)
    private String diplomas;

    @Column(length = 3000)
    private String otherTrainings;

    @ManyToOne
    @JoinColumn(
            name = "formation_id"
    )
    private Formation formation;
}
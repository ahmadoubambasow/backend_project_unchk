package com.unchk.backend.students.entity;

import com.unchk.backend.common.entity.BaseEntity;
import com.unchk.backend.formations.entity.Formation;

import com.unchk.backend.users.entity.User;
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
public class Student extends BaseEntity {

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

    @ManyToOne
    @JoinColumn(
            name = "group_id"
    )
    private StudentGroup group;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
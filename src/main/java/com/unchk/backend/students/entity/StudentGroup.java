package com.unchk.backend.students.entity;

import com.unchk.backend.formations.entity.Formation;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "student_groups")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentGroup {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private String name;

    private String promotion;

    private Integer academicYear;

    @ManyToOne
    @JoinColumn(
            name = "formation_id"
    )
    private Formation formation;
}
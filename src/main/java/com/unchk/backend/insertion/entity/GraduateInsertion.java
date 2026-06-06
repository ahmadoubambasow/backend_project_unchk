package com.unchk.backend.insertion.entity;

import com.unchk.backend.students.entity.Student;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "graduate_insertions")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GraduateInsertion {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "student_id"
    )
    private Student student;

    @Enumerated(
            EnumType.STRING
    )
    private GraduateInsertionStatus status;

    private String company;

    private String position;

    private LocalDate startDate;

    private Double salary;

    @Column(length = 3000)
    private String remarks;
}
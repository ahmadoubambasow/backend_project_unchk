package com.unchk.backend.insertion.entity;

import com.unchk.backend.common.entity.BaseEntity;
import com.unchk.backend.students.entity.Student;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "internships")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Internship extends BaseEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;

    private String supervisor;

    private LocalDate startDate;

    private LocalDate endDate;

    @Column(length = 3000)
    private String evaluation;

    @Column(length = 3000)
    private String remarks;

    @Enumerated(EnumType.STRING)
    private InternshipStatus status;
}
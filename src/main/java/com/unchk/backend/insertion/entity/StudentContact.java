package com.unchk.backend.insertion.entity;

import com.unchk.backend.students.entity.Student;
import com.unchk.backend.users.entity.User;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "student_contacts")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentContact {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    private LocalDate contactDate;

    @Enumerated(
            EnumType.STRING
    )
    private StudentContactType contactType;

    private String subject;

    @Column(length = 3000)
    private String description;
}
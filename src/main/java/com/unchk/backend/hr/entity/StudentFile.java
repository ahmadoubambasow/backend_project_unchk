package com.unchk.backend.hr.entity;

import com.unchk.backend.students.entity.Student;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentFile {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @OneToOne
    @JoinColumn(
            name = "student_id"
    )
    private Student student;

    private String registrationNumber;

    private String guardianName;

    private String guardianPhone;

    private String address;

    private String previousSchool;

    private String birthCertificatePath;

    private String diplomaPath;

    private String photoPath;

    private String remarks;
}
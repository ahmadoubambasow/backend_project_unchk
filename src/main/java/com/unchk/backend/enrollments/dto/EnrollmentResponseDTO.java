package com.unchk.backend.enrollments.dto;

import com.unchk.backend.enrollments.entity.EnrollmentStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentResponseDTO {

    private Long id;

    private String studentName;

    private String matricule;

    private String formationName;

    private String formationCode;

    private LocalDate enrollmentDate;

    private String academicYear;

    private EnrollmentStatus status;

    private LocalDateTime createdAt;
}

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

    /**
     * ID inscription
     */
    private Long id;

    /**
     * Etudiant
     */
    private Long studentId;

    private String studentName;

    private String matricule;

    /**
     * Formation
     */
    private Long formationId;

    private String formationName;

    private String formationCode;

    /**
     * Filière
     */

    private Long filiereId;

    private String filiereName;

    private String filiereCode;

    /**
     * Promotion
     */
    private Long promotionId;

    private String promotionName;

    /**
     * Groupe
     */
    private Long groupId;

    private String groupName;

    /**
     * Date inscription
     */
    private LocalDate enrollmentDate;

    /**
     * Status
     */
    private EnrollmentStatus status;

    /**
     * Date création
     */
    private LocalDateTime createdAt;
}
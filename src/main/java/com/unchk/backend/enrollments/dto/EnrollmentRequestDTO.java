package com.unchk.backend.enrollments.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentRequestDTO {

    /**
     * Etudiant
     */
    @NotNull
    private Long studentId;

    /**
     * Promotion
     */
    @NotNull
    private Long promotionId;

    /**
     * Formation
     */
    @NotNull
    private Long formationId;

    /**
     * Groupe
     */
    @NotNull
    private Long groupId;
}
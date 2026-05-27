package com.unchk.backend.enrollments.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentRequestDTO {

    @NotNull
    private Long studentId;

    @NotNull
    private Long formationId;

    @NotNull
    private String academicYear;
}

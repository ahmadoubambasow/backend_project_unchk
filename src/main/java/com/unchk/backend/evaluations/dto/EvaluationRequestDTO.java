package com.unchk.backend.evaluations.dto;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationRequestDTO {

    @NotBlank
    private String title;

    private String evaluationDate;

    @NotNull
    private Double maxScore;

    @NotNull
    private Double coefficient;

    @NotNull
    private Long subjectId;
}
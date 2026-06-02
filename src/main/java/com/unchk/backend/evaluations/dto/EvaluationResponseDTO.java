package com.unchk.backend.evaluations.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationResponseDTO {

    private Long id;

    private String title;

    private String evaluationDate;

    private Double maxScore;

    private Double coefficient;

    private Long subjectId;

    private String subjectName;

    private Long formationId;

    private String formationName;

    private Long filiereId;

    private String filiereName;
}
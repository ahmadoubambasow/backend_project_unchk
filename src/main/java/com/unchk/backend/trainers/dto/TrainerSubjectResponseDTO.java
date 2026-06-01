package com.unchk.backend.trainers.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainerSubjectResponseDTO {

    private Long id;

    private Long trainerId;

    private String trainerName;

    private Long subjectId;

    private String subjectName;

    private Long formationId;

    private String formationName;

    private Long filiereId;

    private String filiereName;
}
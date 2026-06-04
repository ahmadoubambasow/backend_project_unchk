package com.unchk.backend.formations.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingModuleResponseDTO {

    private Long id;

    private String title;

    private String description;

    private Integer hours;

    private Long formationId;

    private String formationName;
}
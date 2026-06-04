package com.unchk.backend.formations.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingModuleRequestDTO {

    private String title;

    private String description;

    private Integer hours;

    private Long formationId;
}
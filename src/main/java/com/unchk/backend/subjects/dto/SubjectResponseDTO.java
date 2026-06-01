package com.unchk.backend.subjects.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectResponseDTO {

    private Long id;

    private String code;

    private String name;

    private String description;

    private Integer coefficient;

    private Integer hours;

    private Long formationId;

    private String formationName;

    private Long filiereId;

    private String filiereName;
}

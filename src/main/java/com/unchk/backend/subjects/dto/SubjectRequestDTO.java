package com.unchk.backend.subjects.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectRequestDTO {

    @NotBlank
    private  String name;

    private String description;

    private Integer coefficient;

    private Integer hours;

    @NotNull
    private Long formationId;
}

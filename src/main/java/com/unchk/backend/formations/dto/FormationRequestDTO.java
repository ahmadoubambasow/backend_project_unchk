package com.unchk.backend.formations.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormationRequestDTO {

    @NotBlank
    private String name;

    private String description;

    private Integer duration;
}

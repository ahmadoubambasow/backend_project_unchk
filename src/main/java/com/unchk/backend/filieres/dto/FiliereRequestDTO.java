package com.unchk.backend.filieres.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FiliereRequestDTO {

    @NotBlank
    private String name;

    private String description;
}
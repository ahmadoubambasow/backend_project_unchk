package com.unchk.backend.filieres.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FiliereResponseDTO {

    private Long id;

    private String name;

    private String code;

    private String description;
}
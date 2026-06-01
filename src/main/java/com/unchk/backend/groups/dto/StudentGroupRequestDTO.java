package com.unchk.backend.groups.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentGroupRequestDTO {

    /**
     * Nom groupe
     * Ex:
     * L1-A
     * M1-B
     */
    @NotBlank
    private String name;

    /**
     * Capacité
     */
    private Integer capacity;

    /**
     * Formation associée
     */
    @NotNull
    private Long formationId;
}
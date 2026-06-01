package com.unchk.backend.promotions.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionRequestDTO {

    /**
     * Nom promotion
     * Ex : Promotion 2025-2026
     */
    @NotBlank
    private String name;

    /**
     * Année académique
     */
    @NotBlank
    private String academicYear;

    /**
     * Capacité maximale
     */
    private Integer capacity;
}
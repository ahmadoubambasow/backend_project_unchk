package com.unchk.backend.promotions.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionResponseDTO {

    /**
     * ID promotion
     */
    private Long id;

    /**
     * Nom promotion
     * Ex : Promotion 2025-2026
     */
    private String name;

    /**
     * Année académique
     */
    private String academicYear;

    /**
     * Capacité maximale
     */
    private Integer capacity;
}
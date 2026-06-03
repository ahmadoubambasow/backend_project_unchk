package com.unchk.backend.formations.dto;

import com.unchk.backend.formations.entity.FormationLevel;
import com.unchk.backend.formations.entity.FormationType;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormationRequestDTO {

    @NotBlank
    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private FormationType formationType;

    private FormationLevel level;

    private Double fundingAmount;

    private String fundingType;

    private Integer maleCount;

    private Integer femaleCount;

    private String description;
}
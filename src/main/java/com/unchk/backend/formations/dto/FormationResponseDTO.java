package com.unchk.backend.formations.dto;

import com.unchk.backend.formations.entity.FormationLevel;
import com.unchk.backend.formations.entity.FormationType;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormationResponseDTO {

    private Long id;

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

    private List<TrainerResponseDTO> trainers;
}
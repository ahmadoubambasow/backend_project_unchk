package com.unchk.backend.budget.dto;

import com.unchk.backend.budget.entity.BudgetType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BudgetResponseDTO {

    private Long id;

    private Integer year;

    private String title;

    private BudgetType type;

    private Double plannedAmount;

    private Double executedAmount;

    private Double variance;

    private String description;

    private String documentPath;
}
package com.unchk.backend.budget.dto;

import com.unchk.backend.budget.entity.BudgetType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BudgetRequestDTO {

    private Integer year;

    private String title;

    private BudgetType type;

    private Double plannedAmount;

    private Double executedAmount;

    private String description;

    private String documentPath;
}
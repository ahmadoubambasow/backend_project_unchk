package com.unchk.backend.budget.entity;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "budgets")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Budget {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private Integer year;

    private String title;

    @Enumerated(
            EnumType.STRING
    )
    private BudgetType type;

    private Double plannedAmount;

    private Double executedAmount;

    @Column(length = 5000)
    private String description;

    private String documentPath;
}
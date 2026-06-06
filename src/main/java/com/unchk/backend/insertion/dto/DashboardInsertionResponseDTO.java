package com.unchk.backend.insertion.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardInsertionResponseDTO {

    private Long partners;

    private Long internships;

    private Long ongoingInternships;

    private Long completedInternships;

    private Long insertions;

    private Long salaried;

    private Long autoEmployed;

    private Long furtherStudies;

    private Long unemployed;
}
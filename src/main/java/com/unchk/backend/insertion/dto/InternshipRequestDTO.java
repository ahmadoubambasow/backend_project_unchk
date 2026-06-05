package com.unchk.backend.insertion.dto;

import com.unchk.backend.insertion.entity.InternshipStatus;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InternshipRequestDTO {

    private Long studentId;

    private Long partnerId;

    private String supervisor;

    private LocalDate startDate;

    private LocalDate endDate;

    private String evaluation;

    private String remarks;

    private InternshipStatus status;
}
package com.unchk.backend.insertion.dto;

import com.unchk.backend.insertion.entity.InternshipStatus;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InternshipResponseDTO {

    private Long id;

    private Long studentId;

    private String studentName;

    private Long partnerId;

    private String partnerName;

    private String supervisor;

    private LocalDate startDate;

    private LocalDate endDate;

    private String evaluation;

    private String remarks;

    private InternshipStatus status;
}
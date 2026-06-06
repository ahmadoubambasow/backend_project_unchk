package com.unchk.backend.insertion.dto;

import com.unchk.backend.insertion.entity.GraduateInsertionStatus;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GraduateInsertionResponseDTO {

    private Long id;

    private Long studentId;

    private String studentName;

    private GraduateInsertionStatus status;

    private String company;

    private String position;

    private LocalDate startDate;

    private Double salary;

    private String remarks;
}
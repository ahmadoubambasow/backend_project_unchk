package com.unchk.backend.insertion.dto;

import com.unchk.backend.insertion.entity.GraduateInsertionStatus;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GraduateInsertionRequestDTO {

    private Long studentId;

    private GraduateInsertionStatus status;

    private String company;

    private String position;

    private LocalDate startDate;

    private Double salary;

    private String remarks;
}
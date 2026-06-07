package com.unchk.backend.hr.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class PersonnelFileResponseDTO {

    private Long id;

    private Long userId;

    private String fullName;

    private String role;

    private String employeeNumber;

    private String contractType;

    private LocalDate hireDate;

    private String position;

    private Double salary;

    private String diploma;

    private String phone;

    private String address;

    private String filePath;
}
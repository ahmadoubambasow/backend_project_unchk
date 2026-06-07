package com.unchk.backend.hr.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PersonnelFileRequestDTO {

    private Long userId;

    private String contractType;

    private LocalDate hireDate;

    private String position;

    private Double salary;

    private String diploma;

    private String phone;

    private String address;

    private String filePath;
}
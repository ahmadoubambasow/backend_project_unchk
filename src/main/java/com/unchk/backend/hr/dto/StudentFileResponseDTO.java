package com.unchk.backend.hr.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StudentFileResponseDTO {

    private Long id;

    private Long studentId;

    private String fullName;

    private String email;

    private String registrationNumber;

    private String guardianName;

    private String guardianPhone;

    private String address;

    private String previousSchool;

    private String birthCertificatePath;

    private String diplomaPath;

    private String photoPath;

    private String remarks;
}

package com.unchk.backend.hr.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentFileRequestDTO {

    private Long studentId;



    private String guardianName;

    private String guardianPhone;

    private String address;

    private String previousSchool;

    private String birthCertificatePath;

    private String diplomaPath;

    private String photoPath;

    private String remarks;
}

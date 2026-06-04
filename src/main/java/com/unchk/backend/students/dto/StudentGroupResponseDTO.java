package com.unchk.backend.students.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StudentGroupResponseDTO {
    private Long id;

    private String name;

    private String promotion;

    private Integer academicYear;

    private String formationName;

    private Integer studentCount;
}

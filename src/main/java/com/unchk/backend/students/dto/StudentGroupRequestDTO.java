package com.unchk.backend.students.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentGroupRequestDTO {

    private String name;

    private String promotion;

    private Integer academicYear;

    private Long formationId;
}

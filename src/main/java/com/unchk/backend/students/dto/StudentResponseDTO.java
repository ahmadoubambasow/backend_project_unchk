package com.unchk.backend.students.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponseDTO {

    private Long id;

    private String ine;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String promotion;

    private Integer startYear;

    private Integer graduationYear;

    private String diplomas;

    private String otherTrainings;

    private Long formationId;

    private String formationName;

    private Long groupId;

    private String groupName;
}
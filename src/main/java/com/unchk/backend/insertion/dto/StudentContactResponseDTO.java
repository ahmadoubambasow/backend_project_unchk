package com.unchk.backend.insertion.dto;

import com.unchk.backend.insertion.entity.StudentContactType;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentContactResponseDTO {

    private Long id;

    private Long studentId;

    private String studentName;

    private Long createdById;

    private String createdByName;

    private LocalDate contactDate;

    private StudentContactType contactType;

    private String subject;

    private String description;
}
package com.unchk.backend.insertion.dto;

import com.unchk.backend.insertion.entity.StudentContactType;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentContactRequestDTO {

    private Long studentId;

    private Long createdById;

    private LocalDate contactDate;

    private StudentContactType contactType;

    private String subject;

    private String description;
}
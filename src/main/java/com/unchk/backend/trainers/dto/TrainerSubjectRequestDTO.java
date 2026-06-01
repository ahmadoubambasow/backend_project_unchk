package com.unchk.backend.trainers.dto;

import jakarta.validation.constraints.NotNull;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainerSubjectRequestDTO {

    @NotNull
    private Long trainerId;

    @NotNull
    private Long subjectId;
}
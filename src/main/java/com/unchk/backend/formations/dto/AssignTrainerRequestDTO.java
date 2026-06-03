package com.unchk.backend.formations.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignTrainerRequestDTO {

    private Long formationId;

    private Long trainerId;
}
package com.unchk.backend.formations.dto;

import com.unchk.backend.formations.entity.FormationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormationResponseDTO {

    private Long id;

    private String code;

    private String name;

    private  String description;

    private Integer duration;

    private FormationStatus status;

    private LocalDateTime createdAt;
}

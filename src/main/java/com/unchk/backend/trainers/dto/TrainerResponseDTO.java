package com.unchk.backend.trainers.dto;

import com.unchk.backend.trainers.enums.TrainerType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainerResponseDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String speciality;

    private String grade;

    private TrainerType type;
}

package com.unchk.backend.trainers.dto;

import com.unchk.backend.trainers.enums.TrainerType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainerRequestDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;

    private String phone;

    private String speciality;

    private String grade;

    @NotNull
    private TrainerType type;


}

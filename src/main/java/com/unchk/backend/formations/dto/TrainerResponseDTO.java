package com.unchk.backend.formations.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainerResponseDTO {

    private Long id;

    private String fullName;

    private String email;

    private String role;
}
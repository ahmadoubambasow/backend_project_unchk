package com.unchk.backend.insertion.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerResponseDTO {

    private Long id;

    private String name;

    private String contactPerson;

    private String phone;

    private String email;

    private String address;

    private String sector;

    private String description;
}
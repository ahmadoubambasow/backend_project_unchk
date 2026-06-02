package com.unchk.backend.communications.dto;

import com.unchk.backend.communications.entity.CommunicationAccessRole;
import com.unchk.backend.communications.entity.CommunicationType;

import com.unchk.backend.communications.entity.DocumentAccessRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunicationRequestDTO {

    @NotBlank
    private String title;

    @NotNull
    private CommunicationType type;

    private String description;

    private String report;

    @NotBlank
    private String eventDate;

    private String documentName;

    private String documentUrl;

    private String documentType;

    private CommunicationAccessRole accessRole;
}
package com.unchk.backend.communications.dto;

import com.unchk.backend.communications.entity.CommunicationAccessRole;
import com.unchk.backend.communications.entity.CommunicationType;

import com.unchk.backend.communications.entity.DocumentAccessRole;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunicationResponseDTO {

    private Long id;

    private String title;

    private CommunicationType type;

    private String description;

    private String report;

    private String eventDate;

    private String documentName;

    private String documentUrl;

    private String documentType;

    private CommunicationAccessRole accessRole;

    private String createdAt;
}
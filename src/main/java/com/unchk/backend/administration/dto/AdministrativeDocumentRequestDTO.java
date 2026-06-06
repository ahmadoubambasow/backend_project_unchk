package com.unchk.backend.administration.dto;

import com.unchk.backend.administration.entity.DocumentStatus;
import com.unchk.backend.administration.entity.DocumentType;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdministrativeDocumentRequestDTO {

    private String title;

    private DocumentType type;

    private DocumentStatus status;

    private LocalDate documentDate;

    private Long issuerId;

    private Long recipientId;

    private String description;

    private String filePath;
}
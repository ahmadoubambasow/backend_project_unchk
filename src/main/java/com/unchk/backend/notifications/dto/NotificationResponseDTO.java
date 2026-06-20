package com.unchk.backend.notifications.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationResponseDTO {

    private Long id;

    private String title;

    private String message;

    private Boolean isRead;

    private LocalDateTime createdAt;

    // Communication
    private Long communicationId;

    private String communicationTitle;

    private String communicationType;

    private String communicationDescription;

    private String communicationReport;

    private LocalDateTime eventDate;

    private String documentName;

    private String documentUrl;
}
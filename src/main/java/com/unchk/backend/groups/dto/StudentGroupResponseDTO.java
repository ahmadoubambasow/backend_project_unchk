package com.unchk.backend.groups.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentGroupResponseDTO {

    /**
     * ID groupe
     */
    private Long id;

    /**
     * Nom groupe
     */
    private String name;

    /**
     * Capacité
     */
    private Integer capacity;

    /**
     * Formation associée
     */
    private Long formationId;

    private String formationName;

    private Long filiereId;

    private String filiereName;
}
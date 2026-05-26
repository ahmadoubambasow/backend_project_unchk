package com.unchk.backend.dashboard.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardStatsDTO {

    /**
     * Nombre étudiants
     */
    private long students;

    /**
     * Etudiants actifs
     */
    private long activeStudents;

    /**
     * Nombre utilisateurs
     */
    private long users;

    /**
     * Nombre formations
     */
    private long formations;

}

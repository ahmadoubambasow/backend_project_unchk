package com.unchk.backend.dashboard.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardDTO {

    // Role
    private String dashboardType;

    // KPIs
    private Long totalStudents;
    private Long totalUsers;
    private Long totalFormations;
    private Long totalPromotions;
    private Long totalGroups;
    private Long totalPartners;
    private Long totalInternships;
    private Long totalInsertions;
    private Long totalDocuments;

    // Taux
    private Double insertionRate;
    private Double internshipSuccessRate;

    // Répartitions
    private Map<String, Long> internshipsByStatus;
    private Map<String, Long> insertionsByStatus;
    private Map<String, Long> documentsByStatus;
    private Map<String, Long> usersByRole;

    // Graphiques
    private List<DashboardItemDTO> studentsByFormation;
    private List<DashboardItemDTO> studentsByPromotion;
    private List<DashboardItemDTO> studentsByGroup;
    private List<DashboardItemDTO> documentsByType;
    private List<DashboardItemDTO> partnersBySector;
    private List<DashboardItemDTO> teacherModules;

    private long incomingDocuments;

    private long outgoingDocuments;

    private long internalNotes;

    private long administrativeNotes;

    private long circulars;

    private long ongoingInternships;

    private long completedInternships;

    private long cancelledInternships;

    private long salariedInsertions;

    private long autoEmployedInsertions;

    private long unemployedInsertions;

    private long furtherStudiesInsertions;

    private long totalMaleStudents;

    private long totalFemaleStudents;

    private double averageStudentsPerGroup;

    private double averageStudentsPerFormation;

    private String studentIne;

    private String studentFormation;

    private String studentPromotion;

    private String studentGroup;

    private String internshipCompany;

    private String internshipStatus;

    private String insertionStatus;

    private String insertionCompany;

    private String insertionPosition;

    private String studentFullName;

    private String studentEmail;

    private Long totalModules;

    private Long totalSchedules;








}
package com.unchk.backend.dashboard.service.impl;

import com.unchk.backend.administration.entity.DocumentStatus;
import com.unchk.backend.administration.entity.DocumentType;
import com.unchk.backend.administration.repository.AdministrativeDocumentRepository;
import com.unchk.backend.dashboard.dto.DashboardDTO;
import com.unchk.backend.dashboard.dto.DashboardItemDTO;
import com.unchk.backend.dashboard.service.DashboardService;
import com.unchk.backend.formations.repository.FormationRepository;
import com.unchk.backend.insertion.entity.GraduateInsertionStatus;
import com.unchk.backend.insertion.entity.InternshipStatus;
import com.unchk.backend.insertion.repository.GraduateInsertionRepository;
import com.unchk.backend.insertion.repository.InternshipRepository;
import com.unchk.backend.insertion.repository.PartnerRepository;
import com.unchk.backend.promotions.repository.PromotionRepository;
import com.unchk.backend.students.entity.Student;
import com.unchk.backend.students.repository.StudentGroupRepository;
import com.unchk.backend.students.repository.StudentRepository;
import com.unchk.backend.users.entity.Role;
import com.unchk.backend.users.entity.UserRole;
import com.unchk.backend.users.repository.RoleRepository;
import com.unchk.backend.users.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.unchk.backend.users.entity.Roles.SECRETAIRE;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final StudentRepository studentRepository;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final FormationRepository formationRepository;

    private final PromotionRepository promotionRepository;

    private final StudentGroupRepository studentGroupRepository;

    private final PartnerRepository partnerRepository;

    private final InternshipRepository internshipRepository;

    private final GraduateInsertionRepository graduateInsertionRepository;

    private final AdministrativeDocumentRepository administrativeDocumentRepository;


    @Override
    public DashboardDTO getDashboard() {

        UserRole role = getCurrentRole();

        return switch (role) {

            case ADMIN ->
                    buildAdminDashboard();

            case DIRECTION ->
                    buildDirectionDashboard();

            case RESPONSABLE_FORMATION ->
                    buildFormationDashboard();

            case INSERTION ->
                    buildInsertionDashboard();

            case SECRETAIRE ->
                    buildSecretaryDashboard();

            case ETUDIANT ->
                    buildStudentDashboard();

            case ENSEIGNANT,
                 ENSEIGNANT_ASSOCIE,
                 TUTEUR ->
                    buildTeacherDashboard();

            default ->
                    buildAdminDashboard();
        };
    }
    private DashboardDTO buildAdminDashboard() {

        // =========================
        // KPIs
        // =========================

        long totalStudents = studentRepository.count();

        long totalUsers = userRepository.count();

        long totalFormations = formationRepository.count();

        long totalPromotions = promotionRepository.count();

        long totalGroups = studentGroupRepository.count();

        long totalPartners = partnerRepository.count();

        long totalInternships = internshipRepository.count();

        long totalInsertions = graduateInsertionRepository.count();

        long totalDocuments = administrativeDocumentRepository.count();

        // =========================
        // STAGES PAR STATUT
        // =========================

        Map<String, Long> internshipsByStatus = new LinkedHashMap<>();

        for (InternshipStatus status : InternshipStatus.values()) {

            internshipsByStatus.put(
                    status.name(),
                    internshipRepository.countByStatus(status)
            );
        }

        // =========================
        // INSERTIONS PAR STATUT
        // =========================

        Map<String, Long> insertionsByStatus = new LinkedHashMap<>();

        for (GraduateInsertionStatus status :
                GraduateInsertionStatus.values()) {

            insertionsByStatus.put(
                    status.name(),
                    graduateInsertionRepository.countByStatus(status)
            );
        }

        // =========================
        // DOCUMENTS PAR STATUT
        // =========================

        Map<String, Long> documentsByStatus = new LinkedHashMap<>();

        for (DocumentStatus status :
                DocumentStatus.values()) {

            documentsByStatus.put(
                    status.name(),
                    administrativeDocumentRepository.countByStatus(status)
            );
        }

        // =========================
        // UTILISATEURS PAR ROLE
        // =========================

        Map<String, Long> usersByRole = new LinkedHashMap<>();

        for (Role role : roleRepository.findAll()) {

            usersByRole.put(
                    role.getName().name(),
                    userRepository.countByRole_Name(
                            role.getName()
                    )
            );
        }

        // =========================
        // ETUDIANTS PAR FORMATION
        // =========================

        List<DashboardItemDTO> studentsByFormation =
                formationRepository.findAll()
                        .stream()
                        .map(formation ->
                                DashboardItemDTO.builder()
                                        .label(formation.getName())
                                        .value(
                                                (long) studentRepository
                                                        .findByFormationId(
                                                                formation.getId()
                                                        )
                                                        .size()
                                        )
                                        .build()
                        )
                        .toList();

        // =========================
        // ETUDIANTS PAR PROMOTION
        // =========================

        Map<String, Long> promotionMap =
                studentRepository.findAll()
                        .stream()
                        .filter(student ->
                                student.getPromotion() != null
                                        && !student.getPromotion().isBlank()
                        )
                        .collect(Collectors.groupingBy(
                                Student::getPromotion,
                                Collectors.counting()
                        ));

        List<DashboardItemDTO> studentsByPromotion =
                promotionMap.entrySet()
                        .stream()
                        .map(entry ->
                                DashboardItemDTO.builder()
                                        .label(entry.getKey())
                                        .value(entry.getValue())
                                        .build()
                        )
                        .toList();

        // =========================
        // ETUDIANTS PAR GROUPE
        // =========================

        List<DashboardItemDTO> studentsByGroup =
                studentGroupRepository.findAll()
                        .stream()
                        .map(group ->
                                DashboardItemDTO.builder()
                                        .label(group.getName())
                                        .value(
                                                (long) studentRepository
                                                        .findByGroupId(
                                                                group.getId()
                                                        )
                                                        .size()
                                        )
                                        .build()
                        )
                        .toList();

        // =========================
        // DOCUMENTS PAR TYPE
        // =========================

        List<DashboardItemDTO> documentsByType =
                Arrays.stream(DocumentType.values())
                        .map(type ->
                                DashboardItemDTO.builder()
                                        .label(type.name())
                                        .value(
                                                administrativeDocumentRepository
                                                        .countByType(type)
                                        )
                                        .build()
                        )
                        .toList();

        // =========================
        // PARTENAIRES PAR SECTEUR
        // =========================

        Map<String, Long> sectorsMap =
                partnerRepository.findAll()
                        .stream()
                        .collect(Collectors.groupingBy(
                                partner -> {

                                    if (partner.getSector() == null
                                            || partner.getSector().isBlank()) {
                                        return "NON DEFINI";
                                    }

                                    return partner.getSector();
                                },
                                Collectors.counting()
                        ));

        List<DashboardItemDTO> partnersBySector =
                sectorsMap.entrySet()
                        .stream()
                        .map(entry ->
                                DashboardItemDTO.builder()
                                        .label(entry.getKey())
                                        .value(entry.getValue())
                                        .build()
                        )
                        .toList();

        // =========================
        // TAUX D'INSERTION
        // =========================

        long salaried =
                graduateInsertionRepository.countByStatus(
                        GraduateInsertionStatus.SALARIED
                );

        long autoEmployed =
                graduateInsertionRepository.countByStatus(
                        GraduateInsertionStatus.AUTO_EMPLOYED
                );

        double insertionRate =
                totalInsertions == 0
                        ? 0
                        : ((double) (salaried + autoEmployed)
                        / totalInsertions) * 100;

        // =========================
        // TAUX DE REUSSITE STAGES
        // =========================

        long completedInternships =
                internshipRepository.countByStatus(
                        InternshipStatus.COMPLETED
                );

        double internshipSuccessRate =
                totalInternships == 0
                        ? 0
                        : ((double) completedInternships
                        / totalInternships) * 100;

        // =========================
        // DTO FINAL
        // =========================

        return DashboardDTO.builder()

                .dashboardType("ADMIN")

                .totalStudents(totalStudents)
                .totalUsers(totalUsers)
                .totalFormations(totalFormations)
                .totalPromotions(totalPromotions)
                .totalGroups(totalGroups)
                .totalPartners(totalPartners)
                .totalInternships(totalInternships)
                .totalInsertions(totalInsertions)
                .totalDocuments(totalDocuments)

                .insertionRate(
                        Math.round(insertionRate * 100.0) / 100.0
                )

                .internshipSuccessRate(
                        Math.round(internshipSuccessRate * 100.0) / 100.0
                )

                .internshipsByStatus(internshipsByStatus)
                .insertionsByStatus(insertionsByStatus)
                .documentsByStatus(documentsByStatus)
                .usersByRole(usersByRole)

                .studentsByFormation(studentsByFormation)
                .studentsByPromotion(studentsByPromotion)
                .studentsByGroup(studentsByGroup)
                .documentsByType(documentsByType)
                .partnersBySector(partnersBySector)

                .build();
    }

    private UserRole getCurrentRole() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        return authentication
                .getAuthorities()
                .stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .map(role -> role.replace("ROLE_", ""))
                .map(UserRole::valueOf)
                .orElseThrow(() ->
                            new IllegalStateException(
                                    "Role introuvable"
                            )
                        );


    }

    private String getCurrentUserEmail() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        return authentication.getName();
    }

    private DashboardDTO buildTeacherDashboard() {

        return DashboardDTO.builder()
                .dashboardType("TUTEUR")
                .totalStudents(studentRepository.count())
                .totalFormations(formationRepository.count())
                .build();
    }

    private DashboardDTO buildInsertionDashboard() {

        return DashboardDTO.builder()
                .dashboardType("INSERTION")
                .totalPartners(partnerRepository.count())
                .totalInternships(internshipRepository.count())
                .totalInsertions(graduateInsertionRepository.count())
                .build();
    }

    private DashboardDTO buildSecretaryDashboard() {

        return DashboardDTO.builder()
                .dashboardType("SECRETAIRE")
                .totalDocuments(administrativeDocumentRepository.count())
                .build();
    }

    private DashboardDTO buildStudentDashboard() {

        String email = getCurrentUserEmail();

        var user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Utilisateur introuvable"
                        )
                );

        var student = studentRepository
                .findByUserId(user.getId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Dossier étudiant introuvable"
                        )
                );

        var internship =
                internshipRepository
                        .findByStudent_Id(student.getId())
                        .orElse(null);

        var insertion =
                graduateInsertionRepository
                        .findByStudentId(student.getId())
                        .orElse(null);

        return DashboardDTO.builder()

                .dashboardType("ETUDIANT")

                .studentIne(
                        student.getIne()
                )

                .studentFormation(
                        student.getFormation() != null
                                ? student.getFormation().getName()
                                : null
                )

                .studentFullName(
                        user.getFullName()
                )

                .studentEmail(
                        user.getEmail()
                )

                .studentGroup(
                        student.getGroup() != null
                                ? student.getGroup().getName()
                                : null
                )

                .studentPromotion(
                        student.getPromotion()
                )

                // Stage
                .internshipCompany(
                        internship != null &&
                                internship.getPartner() != null
                                ? internship.getPartner().getName()
                                : null
                )

                .internshipStatus(
                        internship != null &&
                                internship.getStatus() != null
                                ? internship.getStatus().name()
                                : null
                )

                // Insertion
                .insertionStatus(
                        insertion != null &&
                                insertion.getStatus() != null
                                ? insertion.getStatus().name()
                                : null
                )

                .insertionCompany(
                        insertion != null
                                ? insertion.getCompany()
                                : null
                )

                .insertionPosition(
                        insertion != null
                                ? insertion.getPosition()
                                : null
                )

                .build();
    }

    private DashboardDTO buildDirectionDashboard() {

        return DashboardDTO.builder()
                .dashboardType("DIRECTION")
                .totalStudents(studentRepository.count())
                .totalFormations(formationRepository.count())
                .totalPartners(partnerRepository.count())
                .totalInsertions(graduateInsertionRepository.count())
                .build();
    }

    private DashboardDTO buildFormationDashboard() {

        return DashboardDTO.builder()
                .dashboardType("RESPONSABLE_FORMATION")
                .totalStudents(studentRepository.count())
                .totalFormations(formationRepository.count())
                .totalGroups(studentGroupRepository.count())
                .totalPromotions(promotionRepository.count())
                .build();
    }


}
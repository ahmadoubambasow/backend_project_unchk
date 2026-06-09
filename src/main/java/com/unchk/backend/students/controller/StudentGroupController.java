package com.unchk.backend.students.controller;

import com.unchk.backend.students.dto.StudentGroupRequestDTO;
import com.unchk.backend.students.dto.StudentGroupResponseDTO;
import com.unchk.backend.students.service.StudentGroupService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST de gestion des groupes d'étudiants.
 */
@RestController
@RequestMapping("/api/student-groups")
@RequiredArgsConstructor
public class StudentGroupController {

    private final StudentGroupService studentGroupService;

    /**
     * Création d'un groupe.
     *
     * Autorisés :
     * - ADMIN
     * - RESPONSABLE_FORMATION
     */
    @PostMapping
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'RESPONSABLE_FORMATION'" +
                    ")"
    )
    public StudentGroupResponseDTO createGroup(

            @RequestBody
            StudentGroupRequestDTO request

    ) {

        return studentGroupService.createGroup(
                request
        );
    }

    /**
     * Liste de tous les groupes.
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     * - ADMINISTRATIF
     * - RESPONSABLE_FORMATION
     * - ENSEIGNANT
     * - TUTEUR
     */
    @GetMapping
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'DIRECTION'," +
                    "'ADMINISTRATIF'," +
                    "'RESPONSABLE_FORMATION'," +
                    "'ENSEIGNANT'," +
                    "'TUTEUR'" +
                    ")"
    )
    public List<StudentGroupResponseDTO>
    getAllGroups() {

        return studentGroupService.getAllGroups();
    }

    /**
     * Consultation du détail d'un groupe.
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     * - ADMINISTRATIF
     * - RESPONSABLE_FORMATION
     * - ENSEIGNANT
     * - TUTEUR
     * - ETUDIANT
     *
     * Remarque :
     * Un étudiant ne devrait pouvoir consulter
     * que son propre groupe.
     */
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public StudentGroupResponseDTO getGroupById(

            @PathVariable
            Long id

    ) {

        return studentGroupService.getGroupById(
                id
        );
    }

    /**
     * Liste des groupes d'une formation.
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     * - ADMINISTRATIF
     * - RESPONSABLE_FORMATION
     * - ENSEIGNANT
     * - TUTEUR
     */
    @GetMapping("/formation/{formationId}")
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'DIRECTION'," +
                    "'ADMINISTRATIF'," +
                    "'RESPONSABLE_FORMATION'," +
                    "'ENSEIGNANT'," +
                    "'TUTEUR'" +
                    ")"
    )
    public List<StudentGroupResponseDTO>
    getGroupsByFormation(

            @PathVariable
            Long formationId

    ) {

        return studentGroupService.getGroupsByFormation(
                formationId
        );
    }

    /**
     * Modification d'un groupe.
     *
     * Autorisés :
     * - ADMIN
     * - RESPONSABLE_FORMATION
     */
    @PutMapping("/{id}")
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'RESPONSABLE_FORMATION'" +
                    ")"
    )
    public StudentGroupResponseDTO updateGroup(

            @PathVariable
            Long id,

            @RequestBody
            StudentGroupRequestDTO request

    ) {

        return studentGroupService.updateGroup(

                id,

                request
        );
    }

    /**
     * Suppression d'un groupe.
     *
     * Autorisés :
     * - ADMIN uniquement
     *
     * Remarque :
     * La suppression d'un groupe peut avoir
     * un impact important sur les emplois du temps,
     * les affectations et les inscriptions.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize(
            "hasRole('ADMIN')"
    )
    public void deleteGroup(

            @PathVariable
            Long id

    ) {

        studentGroupService.deleteGroup(
                id
        );
    }
}
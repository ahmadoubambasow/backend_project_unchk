package com.unchk.backend.students.controller;

import com.unchk.backend.students.dto.StudentRequestDTO;
import com.unchk.backend.students.dto.StudentResponseDTO;
import com.unchk.backend.students.service.StudentService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST de gestion des étudiants.
 */
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    /**
     * Création d'un étudiant.
     *
     * Autorisés :
     * - ADMIN
     * - ADMINISTRATIF
     * - RESPONSABLE_FORMATION
     */
    @PostMapping
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'ADMINISTRATIF'," +
                    "'RESPONSABLE_FORMATION'" +
                    ")"
    )
    public StudentResponseDTO createStudent(

            @RequestBody
            StudentRequestDTO request

    ) {

        return studentService.createStudent(
                request
        );
    }

    /**
     * Liste complète des étudiants.
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
    public List<StudentResponseDTO>
    getAllStudents() {

        return studentService.getAllStudents();
    }

    /**
     * Consultation du détail d'un étudiant.
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     * - ADMINISTRATIF
     * - RESPONSABLE_FORMATION
     * - ENSEIGNANT
     * - TUTEUR
     * - ETUDIANT (son propre dossier uniquement)
     *
     * Remarque :
     * Le service doit empêcher un étudiant
     * d'accéder au dossier d'un autre étudiant.
     */
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public StudentResponseDTO getStudentById(

            @PathVariable
            Long id

    ) {

        return studentService.getStudentById(
                id
        );
    }

    /**
     * Liste des étudiants d'une formation.
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
    public List<StudentResponseDTO>
    getStudentsByFormation(

            @PathVariable
            Long formationId

    ) {

        return studentService.getStudentsByFormation(
                formationId
        );
    }

    /**
     * Modification d'un étudiant.
     *
     * Autorisés :
     * - ADMIN
     * - ADMINISTRATIF
     * - RESPONSABLE_FORMATION
     */
    @PutMapping("/{id}")
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'ADMINISTRATIF'," +
                    "'RESPONSABLE_FORMATION'" +
                    ")"
    )
    public StudentResponseDTO updateStudent(

            @PathVariable
            Long id,

            @RequestBody
            StudentRequestDTO request

    ) {

        return studentService.updateStudent(

                id,

                request
        );
    }

    /**
     * Suppression d'un étudiant.
     *
     * Autorisés :
     * - ADMIN uniquement
     */
    @DeleteMapping("/{id}")
    @PreAuthorize(
            "hasRole('ADMIN')"
    )
    public void deleteStudent(

            @PathVariable
            Long id

    ) {

        studentService.deleteStudent(
                id
        );
    }

    /**
     * Liste des étudiants d'un groupe.
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     * - ADMINISTRATIF
     * - RESPONSABLE_FORMATION
     * - ENSEIGNANT
     * - TUTEUR
     */
    @GetMapping("/group/{groupId}")
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
    public List<StudentResponseDTO>
    getStudentsByGroup(

            @PathVariable
            Long groupId

    ) {

        return studentService.getStudentsByGroup(
                groupId
        );
    }
}
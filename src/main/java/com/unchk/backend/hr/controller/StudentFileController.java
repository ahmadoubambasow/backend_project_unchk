package com.unchk.backend.hr.controller;

import com.unchk.backend.hr.dto.StudentFileRequestDTO;
import com.unchk.backend.hr.dto.StudentFileResponseDTO;
import com.unchk.backend.hr.service.StudentFileService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Controller REST de gestion des dossiers étudiants.
 */
@RestController
@RequestMapping("/api/student-files")
@RequiredArgsConstructor
public class StudentFileController {

    private final StudentFileService service;

    /**
     * Création d'un dossier étudiant.
     *
     * Autorisés :
     * - ADMIN
     * - ADMINISTRATIF
     * - RESPONSABLE_FORMATION
     */
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'ADMINISTRATIF'," +
                    "'RESPONSABLE_FORMATION'" +
                    ")"
    )
    @PostMapping
    public StudentFileResponseDTO create(

            @RequestBody
            StudentFileRequestDTO request

    ) {

        return service.create(
                request
        );
    }

    /**
     * Liste des dossiers étudiants.
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     * - ADMINISTRATIF
     * - RESPONSABLE_FORMATION
     */
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'DIRECTION'," +
                    "'ADMINISTRATIF'," +
                    "'RESPONSABLE_FORMATION'" +
                    ")"
    )
    @GetMapping
    public List<StudentFileResponseDTO> getAll() {

        return service.getAll();
    }

    /**
     * Consultation d'un dossier étudiant.
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     * - ADMINISTRATIF
     * - RESPONSABLE_FORMATION
     */
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'DIRECTION'," +
                    "'ADMINISTRATIF'," +
                    "'RESPONSABLE_FORMATION'" +
                    ")"
    )
    @GetMapping("/{id}")
    public StudentFileResponseDTO getById(

            @PathVariable
            Long id

    ) {

        return service.getById(
                id
        );
    }

    /**
     * Modification d'un dossier étudiant.
     *
     * Autorisés :
     * - ADMIN
     * - ADMINISTRATIF
     * - RESPONSABLE_FORMATION
     */
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'ADMINISTRATIF'," +
                    "'RESPONSABLE_FORMATION'" +
                    ")"
    )
    @PutMapping("/{id}")
    public StudentFileResponseDTO update(

            @PathVariable
            Long id,

            @RequestBody
            StudentFileRequestDTO request

    ) {

        return service.update(
                id,
                request
        );
    }

    /**
     * Suppression d'un dossier étudiant.
     *
     * Autorisés :
     * - ADMIN uniquement
     */
    @PreAuthorize(
            "hasRole('ADMIN')"
    )
    @DeleteMapping("/{id}")
    public void delete(

            @PathVariable
            Long id

    ) {

        service.delete(
                id
        );
    }

    /**
     * Téléversement d'un document lié au dossier étudiant.
     *
     * Autorisés :
     * - ADMIN
     * - ADMINISTRATIF
     * - RESPONSABLE_FORMATION
     */
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'ADMINISTRATIF'," +
                    "'RESPONSABLE_FORMATION'" +
                    ")"
    )
    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>>
    uploadDocument(

            @RequestParam("file")
            MultipartFile file

    ) {

        String filePath =

                service.uploadDocument(
                        file
                );

        return ResponseEntity.ok(

                Map.of(
                        "filePath",
                        filePath
                )
        );
    }
}
package com.unchk.backend.hr.controller;

import com.unchk.backend.hr.dto.PersonnelFileRequestDTO;
import com.unchk.backend.hr.dto.PersonnelFileResponseDTO;
import com.unchk.backend.hr.service.PersonnelFileService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Controller REST de gestion des dossiers du personnel.
 */
@RestController
@RequestMapping("/api/personnel-files")
@RequiredArgsConstructor
public class PersonnelFileController {

    private final PersonnelFileService service;

    /**
     * Création d'un dossier du personnel.
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     * - ADMINISTRATIF
     */
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'DIRECTION'," +
                    "'ADMINISTRATIF'" +
                    ")"
    )
    @PostMapping
    public PersonnelFileResponseDTO create(

            @RequestBody
            PersonnelFileRequestDTO request

    ) {

        return service.create(
                request
        );
    }

    /**
     * Liste des dossiers du personnel.
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     * - ADMINISTRATIF
     */
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'DIRECTION'," +
                    "'ADMINISTRATIF'" +
                    ")"
    )
    @GetMapping
    public List<PersonnelFileResponseDTO> getAll() {

        return service.getAll();
    }

    /**
     * Consultation d'un dossier du personnel.
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     * - ADMINISTRATIF
     */
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'DIRECTION'," +
                    "'ADMINISTRATIF'" +
                    ")"
    )
    @GetMapping("/{id}")
    public PersonnelFileResponseDTO getById(

            @PathVariable
            Long id

    ) {

        return service.getById(
                id
        );
    }

    /**
     * Modification d'un dossier du personnel.
     *
     * Autorisés :
     * - ADMIN
     * - ADMINISTRATIF
     */
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'ADMINISTRATIF'" +
                    ")"
    )
    @PutMapping("/{id}")
    public PersonnelFileResponseDTO update(

            @PathVariable
            Long id,

            @RequestBody
            PersonnelFileRequestDTO request

    ) {

        return service.update(
                id,
                request
        );
    }

    /**
     * Suppression d'un dossier du personnel.
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
     * Téléversement d'un document lié au dossier du personnel.
     *
     * Autorisés :
     * - ADMIN
     * - ADMINISTRATIF
     */
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'ADMINISTRATIF'" +
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
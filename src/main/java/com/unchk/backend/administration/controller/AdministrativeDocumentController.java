package com.unchk.backend.administration.controller;

import com.unchk.backend.administration.dto.AdministrativeDocumentRequestDTO;
import com.unchk.backend.administration.dto.AdministrativeDocumentResponseDTO;
import com.unchk.backend.administration.service.AdministrativeDocumentService;

import com.unchk.backend.administration.service.DocumentStorageService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/administrative-documents")
@RequiredArgsConstructor
public class AdministrativeDocumentController {

    private final AdministrativeDocumentService
            service;
    private final DocumentStorageService documentStorageService;


    /**
     * Créer document administrative
     * @param request
     * @return
     */

    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'SECRETAIRE'," +
                    "'ADMINISTRATIF'" +
                    ")"
    )
    @PostMapping
    public AdministrativeDocumentResponseDTO create(

            @RequestBody
            AdministrativeDocumentRequestDTO request

    ) {

        System.out.println("create Document Executed");
        return service.create(
                request
        );
    }


    /**
     * Retourner la liste des dccuments
     * @return
     */

    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'SECRETAIRE'," +
                    "'ADMINISTRATIF'" +
                    ")"
    )
    @GetMapping
    public List<AdministrativeDocumentResponseDTO>
    getAll() {

        return service.getAll();
    }

    /**
     * Documents par ID
     * @param id
     * @return
     */

    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'DIRECTION'," +
                    "'SECRETAIRE'," +
                    "'ADMINISTRATIF'" +
                    ")"
    )
    @GetMapping("/{id}")
    public AdministrativeDocumentResponseDTO getById(

            @PathVariable
            Long id

    ) {

        return service.getById(id);
    }


    /**
     * Modifier document
     * @param id
     * @param request
     * @return
     */

    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'SECRETAIRE'," +
                    "'ADMINISTRATIF'" +
                    ")"
    )
    @PutMapping("/{id}")
    public AdministrativeDocumentResponseDTO update(

            @PathVariable
            Long id,

            @RequestBody
            AdministrativeDocumentRequestDTO request

    ) {

        return service.update(
                id,
                request
        );
    }


    /**
     * Supprimer document
     * @param id
     */

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(

            @PathVariable
            Long id

    ) {

        service.delete(id);
    }


    /**
     * Upload document
     * @param file
     * @return
     * @throws IOException
     */

    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'SECRETAIRE'," +
                    "'ADMINISTRATIF'" +
                    ")"
    )
    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>>
    uploadDocument(
            @RequestParam("file")
            MultipartFile file
    ) throws IOException {

        String fileName =
                documentStorageService
                        .storeDocument(file);

        return ResponseEntity.ok(
                Map.of(
                        "filePath",
                        fileName
                )
        );
    }


    /**
     * Document de l'utilisateur connecté
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/my-documents")
    public List<AdministrativeDocumentResponseDTO>
    getMyDocuments() {

        return service.getMyDocuments();
    }
}
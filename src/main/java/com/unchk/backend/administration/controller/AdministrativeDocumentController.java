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

    @GetMapping
    public List<AdministrativeDocumentResponseDTO>
    getAll() {

        return service.getAll();
    }

    @GetMapping("/{id}")
    public AdministrativeDocumentResponseDTO getById(

            @PathVariable
            Long id

    ) {

        return service.getById(id);
    }

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

    @DeleteMapping("/{id}")
    public void delete(

            @PathVariable
            Long id

    ) {

        service.delete(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
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

    @GetMapping("/my-documents")
    public List<AdministrativeDocumentResponseDTO>
    getMyDocuments() {

        return service.getMyDocuments();
    }
}
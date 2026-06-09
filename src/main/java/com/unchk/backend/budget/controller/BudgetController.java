package com.unchk.backend.budget.controller;

import com.unchk.backend.budget.dto.BudgetRequestDTO;
import com.unchk.backend.budget.dto.BudgetResponseDTO;
import com.unchk.backend.budget.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService service;

    /**
     * Création d'un budget
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     */
    @PostMapping
    @PreAuthorize(
            "hasAnyRole('ADMIN','DIRECTION')"
    )
    public BudgetResponseDTO create(

            @RequestBody
            BudgetRequestDTO request

    ) {

        return service.create(
                request
        );
    }

    /**
     * Liste des budgets
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     * - RESPONSABLE_FORMATION
     */
    @GetMapping
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'DIRECTION'," +
                    "'RESPONSABLE_FORMATION'" +
                    ")"
    )
    public List<BudgetResponseDTO> getAll() {

        return service.getAll();
    }

    /**
     * Détail d'un budget
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     * - RESPONSABLE_FORMATION
     */
    @GetMapping("/{id}")
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'DIRECTION'," +
                    "'RESPONSABLE_FORMATION'" +
                    ")"
    )
    public BudgetResponseDTO getById(

            @PathVariable
            Long id

    ) {

        return service.getById(
                id
        );
    }

    /**
     * Modification d'un budget
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     */
    @PutMapping("/{id}")
    @PreAuthorize(
            "hasAnyRole('ADMIN','DIRECTION')"
    )
    public BudgetResponseDTO update(

            @PathVariable
            Long id,

            @RequestBody
            BudgetRequestDTO request

    ) {

        return service.update(
                id,
                request
        );
    }

    /**
     * Suppression d'un budget
     *
     * Autorisés :
     * - ADMIN uniquement
     */
    @DeleteMapping("/{id}")
    @PreAuthorize(
            "hasRole('ADMIN')"
    )
    public void delete(

            @PathVariable
            Long id

    ) {

        service.delete(
                id
        );
    }

    /**
     * Téléversement d'un document budgétaire
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     */
    @PostMapping("/upload")
    @PreAuthorize(
            "hasAnyRole('ADMIN','DIRECTION')"
    )
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
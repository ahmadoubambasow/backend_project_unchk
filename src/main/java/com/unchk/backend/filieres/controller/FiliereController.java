package com.unchk.backend.filieres.controller;

import com.unchk.backend.filieres.dto.FiliereRequestDTO;
import com.unchk.backend.filieres.dto.FiliereResponseDTO;
import com.unchk.backend.filieres.service.FiliereService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filieres")
@RequiredArgsConstructor
public class FiliereController {

    private final FiliereService filiereService;

    /**
     * Création
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN','SUPER_ADMIN')"
    )
    @PostMapping
    public FiliereResponseDTO createFiliere(

            @Valid
            @RequestBody
            FiliereRequestDTO request
    ) {

        return filiereService.createFiliere(
                request
        );
    }

    /**
     * Liste
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN','SUPER_ADMIN')"
    )
    @GetMapping
    public List<FiliereResponseDTO>
    getAllFilieres() {

        return filiereService.getAllFilieres();
    }

    /**
     * Modification
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN','SUPER_ADMIN')"
    )
    @PutMapping("/{id}")
    public FiliereResponseDTO updateFiliere(

            @PathVariable
            Long id,

            @Valid
            @RequestBody
            FiliereRequestDTO request
    ) {

        return filiereService.updateFiliere(
                id,
                request
        );
    }

    /**
     * Suppression
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN','SUPER_ADMIN')"
    )
    @DeleteMapping("/{id}")
    public void deleteFiliere(

            @PathVariable
            Long id
    ) {

        filiereService.deleteFiliere(
                id
        );
    }
}
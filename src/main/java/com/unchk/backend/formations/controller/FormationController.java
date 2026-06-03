package com.unchk.backend.formations.controller;

import com.unchk.backend.formations.dto.FormationRequestDTO;
import com.unchk.backend.formations.dto.FormationResponseDTO;
import com.unchk.backend.formations.service.FormationService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formations")
@RequiredArgsConstructor
public class FormationController {

    private final FormationService
            formationService;

    /**
     * Création
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
    )
    @PostMapping
    public FormationResponseDTO
    createFormation(

            @Valid
            @RequestBody
            FormationRequestDTO request

    ) {

        return formationService

                .createFormation(
                        request
                );
    }

    /**
     * Liste
     */
    @GetMapping
    public List<FormationResponseDTO>
    getAllFormations() {

        return formationService

                .getAllFormations();
    }

    /**
     * Détail
     */
    @GetMapping("/{id}")
    public FormationResponseDTO
    getFormationById(

            @PathVariable
            Long id

    ) {

        return formationService

                .getFormationById(
                        id
                );
    }

    /**
     * Modification
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
    )
    @PutMapping("/{id}")
    public FormationResponseDTO
    updateFormation(

            @PathVariable
            Long id,

            @Valid
            @RequestBody
            FormationRequestDTO request

    ) {

        return formationService

                .updateFormation(
                        id,
                        request
                );
    }

    /**
     * Suppression
     */
    @PreAuthorize(
            "hasRole('ADMIN')"
    )
    @DeleteMapping("/{id}")
    public void deleteFormation(

            @PathVariable
            Long id

    ) {

        formationService.deleteFormation(
                id
        );
    }
}
package com.unchk.backend.communications.controller;

import com.unchk.backend.communications.dto.CommunicationRequestDTO;
import com.unchk.backend.communications.dto.CommunicationResponseDTO;
import com.unchk.backend.communications.service.CommunicationService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/communications")
@RequiredArgsConstructor
public class CommunicationController {

    private final CommunicationService communicationService;

    /**
     * Création
     */
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'DIRECTION'," +
                    "'SECRETAIRE'," +
                    "'RESPONSABLE_FORMATION'" +
                    ")"
    )
    @PostMapping
    public CommunicationResponseDTO createCommunication(

            @Valid
            @RequestBody
            CommunicationRequestDTO request
    ) {

        return communicationService
                .createCommunication(
                        request
                );
    }

    /**
     * Liste
     *
     * Accessible à tous les utilisateurs authentifiés.
     * Le service filtre les communications
     * en fonction du rôle de l'utilisateur connecté.
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<CommunicationResponseDTO>
    getAllCommunications() {

        return communicationService
                .getAllCommunications();
    }

    /**
     * Modification
     */
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'DIRECTION'," +
                    "'SECRETAIRE'" +
                    ")"
    )
    @PutMapping("/{id}")
    public CommunicationResponseDTO updateCommunication(

            @PathVariable
            Long id,

            @Valid
            @RequestBody
            CommunicationRequestDTO request
    ) {

        return communicationService
                .updateCommunication(
                        id,
                        request
                );
    }


    /**
     * Archives
     *
     * Accessible à tous les utilisateurs authentifiés.
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/archives")
    public List<CommunicationResponseDTO>
    getArchives() {

        return communicationService
                .getArchives();
    }

    /**
     * Suppression
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteCommunication(

            @PathVariable
            Long id
    ) {

        communicationService.deleteCommunication(
                id
        );
    }
}
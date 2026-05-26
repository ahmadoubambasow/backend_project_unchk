package com.unchk.backend.formations.controller;

import com.unchk.backend.formations.dto.FormationRequestDTO;
import com.unchk.backend.formations.dto.FormationResponseDTO;
import com.unchk.backend.formations.service.FormationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formations")
@RequiredArgsConstructor
public class FormationController {

    private final FormationService  formationService;

    /**
     * Création formation
     */
    @PreAuthorize(
            "hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')"
    )
    @PostMapping
    public FormationResponseDTO createFormation(
            @Valid
            @RequestBody
            FormationRequestDTO request
    ) {

        return formationService.createFormation(request);
    }

    /**
     * Liste formations
     */
    @GetMapping
    public List<FormationResponseDTO> getAllFormations() {

        return formationService.getAllFormations();
    }
}

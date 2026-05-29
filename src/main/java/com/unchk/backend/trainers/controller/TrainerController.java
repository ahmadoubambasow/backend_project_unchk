package com.unchk.backend.trainers.controller;

import com.unchk.backend.trainers.dto.TrainerRequestDTO;
import com.unchk.backend.trainers.dto.TrainerResponseDTO;
import com.unchk.backend.trainers.service.TrainerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;

    /**
     * Création formateur
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN', 'SUPER_ADMIN')"
    )
    @PostMapping
    public TrainerResponseDTO createTrainer(
            @Valid
            @RequestBody
            TrainerRequestDTO request
    ) {
        return trainerService.createTrainer(request);
    }

    /**
     * Liste formateurs
     */
    @GetMapping
    public List<TrainerResponseDTO> getAllTrainers() {

        return trainerService.getAllTrainers();
    }

    /**
     * Mise à jour formateur
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN', 'SUPER_ADMIN')"
    )
    @PutMapping("/{id}")
    public TrainerResponseDTO updateTrainer(

            @PathVariable Long id,
            @Valid
            @RequestBody
            TrainerRequestDTO request
    ) {

        return trainerService.updateTrainer(id, request);
    }

    /**
     * Suppression formateur
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN', 'SUPER_ADMIN')"
    )
    @DeleteMapping("/{id}")
    public void deleteTrainer(

            @PathVariable Long id
    ) {

        trainerService.deleteTrainer(id);
    }
}

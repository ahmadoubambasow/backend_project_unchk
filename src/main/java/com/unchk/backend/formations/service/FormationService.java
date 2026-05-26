package com.unchk.backend.formations.service;

import com.unchk.backend.formations.dto.*;
import com.unchk.backend.formations.entity.*;
import com.unchk.backend.formations.repository.FormationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FormationService {

    private final FormationRepository formationRepository;

    /**
     * Création formation
     */
    public FormationResponseDTO createFormation(FormationRequestDTO request) {

        Formation formation = Formation.builder()
                .code(generateCode())
                .name(request.getName())
                .description(request.getDescription())
                .duration(request.getDuration())
                .status(FormationStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .build();

        Formation savedFormation = formationRepository.save(formation);

        return mapToResponse(savedFormation);
    }

    /**
     * Liste formations
     */
    public List<FormationResponseDTO> getAllFormations() {

        return formationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Génération code formation
     */
    private String generateCode() {

        long count = formationRepository.count() + 1;

        return String.format(
                "FRM2026%03d",
                count
        );
    }

    /**
     * Conversion entity -> DTO
     */
    private FormationResponseDTO mapToResponse(Formation formation) {

        return FormationResponseDTO.builder()

                .id(formation.getId())

                .code(formation.getCode())

                .name(formation.getName())

                .description(formation.getDescription())

                .duration(formation.getDuration())

                .status(formation.getStatus())

                .createdAt(formation.getCreatedAt())

                .build();
    }
}

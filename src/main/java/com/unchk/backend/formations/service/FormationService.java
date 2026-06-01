package com.unchk.backend.formations.service;

import com.unchk.backend.filieres.entity.Filiere;
import com.unchk.backend.filieres.repository.FiliereRepository;
import com.unchk.backend.formations.dto.FormationRequestDTO;
import com.unchk.backend.formations.dto.FormationResponseDTO;
import com.unchk.backend.formations.entity.Formation;
import com.unchk.backend.formations.entity.FormationStatus;
import com.unchk.backend.formations.repository.FormationRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FormationService {

    private final FormationRepository formationRepository;

    private final FiliereRepository filiereRepository;

    /**
     * Création formation
     */
    public FormationResponseDTO createFormation(
            FormationRequestDTO request
    ) {

        Filiere filiere =

                filiereRepository.findById(
                                request.getFiliereId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Filière introuvable"
                                )
                        );

        Formation formation = Formation.builder()

                .code(generateCode())

                .name(
                        request.getName()
                )

                .description(
                        request.getDescription()
                )

                .duration(
                        request.getDuration()
                )

                .status(
                        FormationStatus.ACTIVE
                )

                .createdAt(
                        LocalDateTime.now()
                )

                .filiere(
                        filiere
                )

                .build();

        Formation savedFormation =

                formationRepository.save(
                        formation
                );

        return mapToResponse(
                savedFormation
        );
    }

    /**
     * Liste formations
     */
    public List<FormationResponseDTO>
    getAllFormations() {

        return formationRepository.findAll()

                .stream()

                .map(this::mapToResponse)

                .toList();
    }

    /**
     * Mise à jour formation
     */
    public FormationResponseDTO updateFormation(

            Long id,

            FormationRequestDTO request
    ) {

        Formation formation =

                formationRepository.findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Formation introuvable"
                                )
                        );

        Filiere filiere =

                filiereRepository.findById(
                                request.getFiliereId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Filière introuvable"
                                )
                        );

        formation.setName(
                request.getName()
        );

        formation.setDescription(
                request.getDescription()
        );

        formation.setDuration(
                request.getDuration()
        );

        formation.setFiliere(
                filiere
        );

        Formation updatedFormation =

                formationRepository.save(
                        formation
                );

        return mapToResponse(
                updatedFormation
        );
    }

    /**
     * Suppression formation
     */
    public void deleteFormation(
            Long id
    ) {

        Formation formation =

                formationRepository.findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Formation introuvable"
                                )
                        );

        formationRepository.delete(
                formation
        );
    }

    /**
     * Génération code formation
     */
    private String generateCode() {

        long count =

                formationRepository.count() + 1;

        return String.format(
                "FRM2026%03d",
                count
        );
    }

    /**
     * Conversion Entity -> DTO
     */
    private FormationResponseDTO mapToResponse(
            Formation formation
    ) {

        return FormationResponseDTO.builder()

                .id(
                        formation.getId()
                )

                .code(
                        formation.getCode()
                )

                .name(
                        formation.getName()
                )

                .description(
                        formation.getDescription()
                )

                .duration(
                        formation.getDuration()
                )

                .status(
                        formation.getStatus()
                )

                .createdAt(
                        formation.getCreatedAt()
                )

                .filiereId(
                        formation.getFiliere().getId()
                )

                .filiereName(
                        formation.getFiliere().getName()
                )

                .filiereCode(
                        formation.getFiliere().getCode()
                )

                .build();
    }

    public List<FormationResponseDTO>
    getFormationsByFiliere(
            Long filiereId
    ) {

        return formationRepository

                .findByFiliereId(
                        filiereId
                )

                .stream()

                .map(this::mapToResponse)

                .toList();
    }
}
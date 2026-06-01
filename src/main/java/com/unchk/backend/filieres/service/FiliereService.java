package com.unchk.backend.filieres.service;

import com.unchk.backend.filieres.dto.FiliereRequestDTO;
import com.unchk.backend.filieres.dto.FiliereResponseDTO;
import com.unchk.backend.filieres.entity.Filiere;
import com.unchk.backend.filieres.repository.FiliereRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FiliereService {

    private final FiliereRepository filiereRepository;

    /**
     * Création filière
     */
    public FiliereResponseDTO createFiliere(
            FiliereRequestDTO request
    ) {

        filiereRepository.findByCode(
                        generateCode()
                )

                .ifPresent(filiere -> {

                    throw new RuntimeException(
                            "Cette filière existe déjà"
                    );
                });

        Filiere filiere = Filiere.builder()

                .name(
                        request.getName()
                )

                .code(
                        generateCode()
                )

                .description(
                        request.getDescription()
                )

                .build();

        Filiere savedFiliere =

                filiereRepository.save(
                        filiere
                );

        return mapToResponse(
                savedFiliere
        );
    }

    /**
     * Liste filières
     */
    public List<FiliereResponseDTO>
    getAllFilieres() {

        return filiereRepository.findAll()

                .stream()

                .map(this::mapToResponse)

                .toList();
    }

    /**
     * Mise à jour
     */
    public FiliereResponseDTO updateFiliere(

            Long id,

            FiliereRequestDTO request
    ) {

        Filiere filiere =

                filiereRepository.findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Filière introuvable"
                                )
                        );

        filiere.setName(
                request.getName()
        );

        filiere.setDescription(
                request.getDescription()
        );

        Filiere updatedFiliere =

                filiereRepository.save(
                        filiere
                );

        return mapToResponse(
                updatedFiliere
        );
    }

    /**
     * Suppression
     */
    public void deleteFiliere(
            Long id
    ) {

        Filiere filiere =

                filiereRepository.findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Filière introuvable"
                                )
                        );

        filiereRepository.delete(
                filiere
        );
    }

    /**
     * Mapping
     */
    private FiliereResponseDTO mapToResponse(
            Filiere filiere
    ) {

        return FiliereResponseDTO

                .builder()

                .id(
                        filiere.getId()
                )

                .name(
                        filiere.getName()
                )

                .code(
                        filiere.getCode()
                )

                .description(
                        filiere.getDescription()
                )

                .build();
    }

    /**
     * Génération code filière
     */
    private String generateCode() {

        long count = filiereRepository.count() + 1;

        return String.format(
                "FIL2026%03d", count
        );
    }
}
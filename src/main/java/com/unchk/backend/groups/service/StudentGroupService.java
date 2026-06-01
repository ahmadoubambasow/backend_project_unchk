package com.unchk.backend.groups.service;


import com.unchk.backend.groups.dto.StudentGroupRequestDTO;
import com.unchk.backend.groups.dto.StudentGroupResponseDTO;
import com.unchk.backend.formations.entity.Formation;
import com.unchk.backend.formations.repository.FormationRepository;
import com.unchk.backend.groups.entity.StudentGroup;
import com.unchk.backend.groups.repository.StudentGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentGroupService {

    private final StudentGroupRepository studentGroupRepository;

    private final FormationRepository formationRepository;

    /**
     * Création groupe.
     */
    public StudentGroupResponseDTO createGroup(
            StudentGroupRequestDTO request
    ) {

        Formation formation =

                formationRepository.findById(
                                request.getFormationId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Formation introuvable"
                                )
                        );

        StudentGroup group =

                StudentGroup.builder()

                        .name(
                                request.getName()
                        )

                        .capacity(
                                request.getCapacity()
                        )

                        .formation(
                                formation
                        )

                        .build();

        StudentGroup savedGroup =

                studentGroupRepository.save(
                        group
                );

        return mapToResponse(
                savedGroup
        );
    }

    /**
     * Liste groupes.
     */
    public List<StudentGroupResponseDTO>
    getAllGroups() {

        return studentGroupRepository.findAll()

                .stream()

                .map(this::mapToResponse)

                .toList();
    }


    /**
     * Groupe d'une formation
     */
    public List<StudentGroupResponseDTO>
    getGroupsByFormation(
            Long formationId
    ) {

        return studentGroupRepository

                .findByFormationId(
                        formationId
                )

                .stream()

                .map(this::mapToResponse)

                .toList();
    }

    /**
     * Mise à jour groupe.
     */
    public StudentGroupResponseDTO updateGroup(

            Long id,

            StudentGroupRequestDTO request
    ) {

        StudentGroup group =

                studentGroupRepository.findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Groupe introuvable"
                                )
                        );

        Formation formation =

                formationRepository.findById(
                                request.getFormationId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Formation introuvable"
                                )
                        );

        group.setName(
                request.getName()
        );

        group.setCapacity(
                request.getCapacity()
        );

        group.setFormation(
                formation
        );

        StudentGroup updatedGroup =

                studentGroupRepository.save(
                        group
                );

        return mapToResponse(
                updatedGroup
        );
    }

    /**
     * Suppression groupe.
     */
    public void deleteGroup(
            Long id
    ) {

        StudentGroup group =

                studentGroupRepository.findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Groupe introuvable"
                                )
                        );

        studentGroupRepository.delete(
                group
        );
    }

    /**
     * Mapping Entity -> DTO.
     */
    private StudentGroupResponseDTO mapToResponse(
            StudentGroup group
    ) {

        return StudentGroupResponseDTO

                .builder()

                .id(
                        group.getId()
                )

                .name(
                        group.getName()
                )

                .capacity(
                        group.getCapacity()
                )

                .formationId(
                        group.getFormation().getId()
                )

                .formationName(
                        group.getFormation().getName()
                )

                .filiereId(
                        group.getFormation().getFiliere().getId()
                )

                .filiereName(group.getFormation().getFiliere().getName())

                .build();
    }
}

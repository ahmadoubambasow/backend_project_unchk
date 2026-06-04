package com.unchk.backend.students.service;

import com.unchk.backend.formations.entity.Formation;
import com.unchk.backend.formations.repository.FormationRepository;
import com.unchk.backend.students.dto.StudentGroupRequestDTO;
import com.unchk.backend.students.dto.StudentGroupResponseDTO;
import com.unchk.backend.students.entity.StudentGroup;
import com.unchk.backend.students.repository.StudentGroupRepository;
import com.unchk.backend.students.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentGroupService {

    private final StudentGroupRepository
            studentGroupRepository;

    private final StudentRepository
            studentRepository;

    private final FormationRepository
            formationRepository;

    /**
     * Création groupe
     */
    public StudentGroupResponseDTO
    createGroup(

            StudentGroupRequestDTO request

    ) {

        Formation formation =

                formationRepository

                        .findById(
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

                        .promotion(
                                request.getPromotion()
                        )

                        .academicYear(
                                request.getAcademicYear()
                        )

                        .formation(
                                formation
                        )

                        .build();

        group = studentGroupRepository.save(
                group
        );

        return mapToResponse(
                group
        );
    }

    /**
     * Liste groupes
     */
    public List<StudentGroupResponseDTO>
    getAllGroups() {

        return studentGroupRepository

                .findAll()

                .stream()

                .map(
                        this::mapToResponse
                )

                .toList();
    }

    /**
     * Détail groupe
     */
    public StudentGroupResponseDTO
    getGroupById(

            Long id

    ) {

        StudentGroup group =

                studentGroupRepository

                        .findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Groupe introuvable"
                                )
                        );

        return mapToResponse(
                group
        );
    }

    /**
     * Groupes par formation
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

                .map(
                        this::mapToResponse
                )

                .toList();
    }

    /**
     * Modification
     */
    public StudentGroupResponseDTO
    updateGroup(

            Long id,

            StudentGroupRequestDTO request

    ) {

        StudentGroup group =

                studentGroupRepository

                        .findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Groupe introuvable"
                                )
                        );

        Formation formation =

                formationRepository

                        .findById(
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

        group.setPromotion(
                request.getPromotion()
        );

        group.setAcademicYear(
                request.getAcademicYear()
        );

        group.setFormation(
                formation
        );

        group = studentGroupRepository.save(
                group
        );

        return mapToResponse(
                group
        );
    }

    /**
     * Suppression
     */
    public void deleteGroup(

            Long id

    ) {

        StudentGroup group =

                studentGroupRepository

                        .findById(id)

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
     * Mapping DTO
     */
    private StudentGroupResponseDTO
    mapToResponse(

            StudentGroup group

    ) {

        int studentCount =

                studentRepository

                        .findAll()

                        .stream()

                        .filter(student ->

                                student.getGroup() != null

                                        &&

                                        student.getGroup()
                                                .getId()
                                                .equals(
                                                        group.getId()
                                                )
                        )

                        .toList()

                        .size();

        return StudentGroupResponseDTO

                .builder()

                .id(
                        group.getId()
                )

                .name(
                        group.getName()
                )

                .promotion(
                        group.getPromotion()
                )

                .academicYear(
                        group.getAcademicYear()
                )

                .formationName(
                        group.getFormation()
                                .getName()
                )

                .studentCount(
                        studentCount
                )

                .build();
    }
}
package com.unchk.backend.subjects.service;

import com.unchk.backend.formations.entity.Formation;
import com.unchk.backend.formations.repository.FormationRepository;
import com.unchk.backend.subjects.dto.SubjectRequestDTO;
import com.unchk.backend.subjects.dto.SubjectResponseDTO;
import com.unchk.backend.subjects.entity.Subject;
import com.unchk.backend.subjects.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final FormationRepository formationRepository;

    /**
     * Création matière
     */
    public SubjectResponseDTO createSubject(SubjectRequestDTO request) {

        System.out.println(
                "Formation reçue : "
                        + request.getFormationId()
        );

        Formation formation = formationRepository.findById(request.getFormationId())
                .orElseThrow(() -> new RuntimeException("Formation introuvable"));

        Subject subject = Subject.builder()

                .code(
                        generateCode()
                )

                .name(
                        request.getName()
                )

                .description(
                        request.getDescription()
                )

                .coefficient(
                        request.getCoefficient()
                )

                .hours(
                        request.getHours()
                )

                .formation(formation)

                .build();

        Subject savedSubject = subjectRepository.save(subject);

        return mapToResponse(savedSubject);
    }

    /**
     * Liste matières
     */
    public List<SubjectResponseDTO> getAllSubjects() {

        return subjectRepository.findAll()

                .stream()

                .map(this::mapToResponse)

                .toList();
    }

    /**
     * Matières d'une formation
     */
    public List<SubjectResponseDTO> getSubjectsByFormation(Long formationId) {

        return subjectRepository.findByFormationId(formationId)

                .stream()

                .map(this::mapToResponse)

                .toList();
    }

    /**
     * Modification matière
     */
    public SubjectResponseDTO updateSubject(Long id, SubjectRequestDTO request) {

        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matière introuvable"));

        Formation formation = formationRepository.findById(
                                request.getFormationId()
                        )
                .orElseThrow(() -> new RuntimeException("Formation introuvable"));

        subject.setName(request.getName());
        subject.setDescription(request.getDescription());
        subject.setCoefficient(request.getCoefficient());
        subject.setHours(request.getHours());
        subject.setFormation(formation);

        Subject  updatedSubject = subjectRepository.save(subject);

        return mapToResponse(updatedSubject);
    }

    /**
     * Suppression matière
     */
    public void deleteSubject(Long id) {

        Subject subject = subjectRepository.findById(id)

                .orElseThrow(() -> new RuntimeException("Matière introuvable"));

        subjectRepository.delete(subject);
    }

    /**
     * Génération code
     */
    private String generateCode() {

        long count = subjectRepository.count() + 1;

        return String.format(
                "MAT2026%03d", count
        );
    }

    /**
     * Mapping
     */
    private SubjectResponseDTO mapToResponse(Subject subject) {

        return SubjectResponseDTO.builder()

                .id(
                        (long) subject.getId()
                )

                .code(
                        subject.getCode()
                )

                .name(
                        subject.getName()
                )

                .description(
                        subject.getDescription()
                )

                .coefficient(
                        subject.getCoefficient()
                )

                .hours(
                        subject.getHours()
                )

                .formationId(

                        subject.getFormation().getId()
                )

                .formationName(
                        subject.getFormation().getName()
                )

                .filiereId(
                        subject.getFormation().getFiliere().getId()
                )

                .filiereName(
                        subject.getFormation().getFiliere().getName()
                )

                .build();
    }
}

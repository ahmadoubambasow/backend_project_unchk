package com.unchk.backend.insertion.service;

import com.unchk.backend.insertion.dto.InternshipRequestDTO;
import com.unchk.backend.insertion.dto.InternshipResponseDTO;
import com.unchk.backend.insertion.entity.Internship;
import com.unchk.backend.insertion.repository.InternshipRepository;
import com.unchk.backend.insertion.entity.Partner;
import com.unchk.backend.insertion.repository.PartnerRepository;
import com.unchk.backend.students.entity.Student;
import com.unchk.backend.students.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InternshipService {

    private final InternshipRepository
            internshipRepository;

    private final StudentRepository
            studentRepository;

    private final PartnerRepository
            partnerRepository;

    /**
     * Création
     */
    public InternshipResponseDTO create(

            InternshipRequestDTO request

    ) {

        Student student =

                studentRepository

                        .findById(
                                request.getStudentId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Étudiant introuvable"
                                )
                        );

        Partner partner =

                partnerRepository

                        .findById(
                                request.getPartnerId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Partenaire introuvable"
                                )
                        );

        Internship internship =

                Internship.builder()

                        .student(student)

                        .partner(partner)

                        .supervisor(
                                request.getSupervisor()
                        )

                        .startDate(
                                request.getStartDate()
                        )

                        .endDate(
                                request.getEndDate()
                        )

                        .evaluation(
                                request.getEvaluation()
                        )

                        .remarks(
                                request.getRemarks()
                        )

                        .status(
                                request.getStatus()
                        )

                        .build();

        internship = internshipRepository.save(
                internship
        );

        return mapToResponse(
                internship
        );
    }

    /**
     * Modification
     */
    public InternshipResponseDTO update(

            Long id,

            InternshipRequestDTO request

    ) {

        Internship internship =

                internshipRepository

                        .findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Stage introuvable"
                                )
                        );

        Student student =

                studentRepository

                        .findById(
                                request.getStudentId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Étudiant introuvable"
                                )
                        );

        Partner partner =

                partnerRepository

                        .findById(
                                request.getPartnerId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Partenaire introuvable"
                                )
                        );

        internship.setStudent(
                student
        );

        internship.setPartner(
                partner
        );

        internship.setSupervisor(
                request.getSupervisor()
        );

        internship.setStartDate(
                request.getStartDate()
        );

        internship.setEndDate(
                request.getEndDate()
        );

        internship.setEvaluation(
                request.getEvaluation()
        );

        internship.setRemarks(
                request.getRemarks()
        );

        internship.setStatus(
                request.getStatus()
        );

        internship = internshipRepository.save(
                internship
        );

        return mapToResponse(
                internship
        );
    }

    /**
     * Liste
     */
    public List<InternshipResponseDTO>
    getAll() {

        return internshipRepository

                .findAll()

                .stream()

                .map(
                        this::mapToResponse
                )

                .toList();
    }

    /**
     * Détail
     */
    public InternshipResponseDTO
    getById(

            Long id

    ) {

        Internship internship =

                internshipRepository

                        .findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Stage introuvable"
                                )
                        );

        return mapToResponse(
                internship
        );
    }

    /**
     * Suppression
     */
    public void delete(

            Long id

    ) {

        internshipRepository.deleteById(
                id
        );
    }

    /**
     * Mapping DTO
     */
    private InternshipResponseDTO
    mapToResponse(

            Internship internship

    ) {

        return InternshipResponseDTO

                .builder()

                .id(
                        internship.getId()
                )

                .studentId(
                        internship.getStudent().getId()
                )

                .studentName(

                        internship.getStudent()
                                .getUser() != null
                                ? internship.getStudent()
                                .getUser()
                                .getFullName()

                                : null
                )

                .partnerId(
                        internship.getPartner().getId()
                )

                .partnerName(
                        internship.getPartner().getName()
                )

                .supervisor(
                        internship.getSupervisor()
                )

                .startDate(
                        internship.getStartDate()
                )

                .endDate(
                        internship.getEndDate()
                )

                .evaluation(
                        internship.getEvaluation()
                )

                .remarks(
                        internship.getRemarks()
                )

                .status(
                        internship.getStatus()
                )

                .build();
    }
}
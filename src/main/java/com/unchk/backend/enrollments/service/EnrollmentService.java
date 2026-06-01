package com.unchk.backend.enrollments.service;

import com.unchk.backend.enrollments.dto.EnrollmentRequestDTO;
import com.unchk.backend.enrollments.dto.EnrollmentResponseDTO;
import com.unchk.backend.enrollments.entity.Enrollment;
import com.unchk.backend.enrollments.entity.EnrollmentStatus;
import com.unchk.backend.enrollments.repository.EnrollmentRepository;

import com.unchk.backend.formations.entity.Formation;
import com.unchk.backend.formations.repository.FormationRepository;

import com.unchk.backend.groups.entity.StudentGroup;
import com.unchk.backend.groups.repository.StudentGroupRepository;

import com.unchk.backend.promotions.entity.Promotion;
import com.unchk.backend.promotions.repository.PromotionRepository;

import com.unchk.backend.students.entity.Student;
import com.unchk.backend.students.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    private final StudentRepository studentRepository;

    private final PromotionRepository promotionRepository;

    private final FormationRepository formationRepository;

    private final StudentGroupRepository groupRepository;

    /**
     * Création inscription.
     */
    public EnrollmentResponseDTO createEnrollment(
            EnrollmentRequestDTO request
    ) {

        Student student =

                studentRepository.findById(
                                request.getStudentId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Etudiant introuvable"
                                )
                        );

        Promotion promotion =

                promotionRepository.findById(
                                request.getPromotionId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Promotion introuvable"
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

        StudentGroup group =

                groupRepository.findById(
                                request.getGroupId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Groupe introuvable"
                                )
                        );

        /**
         * Vérifie cohérence
         * Formation <-> Groupe
         */
        if (

                !group.getFormation()
                        .getId()
                        .equals(
                                formation.getId()
                        )

        ) {

            throw new RuntimeException(
                    "Le groupe n'appartient pas à cette formation"
            );
        }

        /**
         * Vérifie doublon.
         */
        if (

                enrollmentRepository

                        .existsByStudentIdAndPromotionIdAndFormationId(

                                student.getId(),

                                promotion.getId(),

                                formation.getId()
                        )

        ) {

            throw new RuntimeException(
                    "Etudiant déjà inscrit dans cette formation"
            );
        }

        Enrollment enrollment =

                Enrollment.builder()

                        .student(
                                student
                        )

                        .formation(
                                formation
                        )

                        .promotion(
                                promotion
                        )

                        .group(
                                group
                        )

                        .enrollmentDate(
                                LocalDate.now()
                        )

                        .status(
                                EnrollmentStatus.ACTIVE
                        )

                        .createdAt(
                                LocalDateTime.now()
                        )

                        .build();

        long currentSize =

                enrollmentRepository

                        .countByGroupId(
                                group.getId()
                        );

        if (

                group.getCapacity() != null

                        &&

                        currentSize >= group.getCapacity()

        ) {

            throw new RuntimeException(
                    "Le groupe est complet"
            );
        }

        Enrollment savedEnrollment =

                enrollmentRepository.save(
                        enrollment
                );

        return mapToResponse(
                savedEnrollment
        );
    }

    /**
     * Liste inscriptions.
     */
    public List<EnrollmentResponseDTO>
    getAllEnrollments() {

        return enrollmentRepository.findAll()

                .stream()

                .map(this::mapToResponse)

                .toList();
    }

    /**
     * Mise à jour inscription.
     */
    public EnrollmentResponseDTO updateEnrollment(

            Long id,

            EnrollmentRequestDTO request
    ) {

        Enrollment enrollment =

                enrollmentRepository.findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Inscription introuvable"
                                )
                        );

        Student student =

                studentRepository.findById(
                                request.getStudentId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Etudiant introuvable"
                                )
                        );

        Promotion promotion =

                promotionRepository.findById(
                                request.getPromotionId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Promotion introuvable"
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

        StudentGroup group =

                groupRepository.findById(
                                request.getGroupId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Groupe introuvable"
                                )
                        );

        if (

                !group.getFormation()
                        .getId()
                        .equals(
                                formation.getId()
                        )

        ) {

            throw new RuntimeException(
                    "Le groupe n'appartient pas à cette formation"
            );
        }

        enrollment.setStudent(
                student
        );

        enrollment.setPromotion(
                promotion
        );

        enrollment.setFormation(
                formation
        );

        enrollment.setGroup(
                group
        );

        Enrollment updatedEnrollment =

                enrollmentRepository.save(
                        enrollment
                );

        return mapToResponse(
                updatedEnrollment
        );
    }

    /**
     * Suppression inscription.
     */
    public void deleteEnrollment(
            Long id
    ) {

        Enrollment enrollment =

                enrollmentRepository.findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Inscription introuvable"
                                )
                        );

        enrollmentRepository.delete(
                enrollment
        );
    }

    /**
     * Mapping.
     */
    private EnrollmentResponseDTO mapToResponse(
            Enrollment enrollment
    ) {

        return EnrollmentResponseDTO

                .builder()

                .id(
                        enrollment.getId()
                )

                .studentId(
                        enrollment.getStudent().getId()
                )

                .studentName(

                        enrollment.getStudent().getFirstName()

                                + " " +

                                enrollment.getStudent().getLastName()
                )

                .matricule(
                        enrollment.getStudent().getMatricule()
                )

                .formationId(
                        enrollment.getFormation().getId()
                )

                .formationName(
                        enrollment.getFormation().getName()
                )

                .formationCode(
                        enrollment.getFormation().getCode()
                )

                .filiereId(
                        enrollment.getFormation().getFiliere().getId()
                )

                .filiereName(
                        enrollment.getFormation().getFiliere().getName()
                )

                .filiereCode(
                        enrollment.getFormation().getFiliere().getCode()
                )

                .promotionId(
                        enrollment.getPromotion().getId()
                )

                .promotionName(
                        enrollment.getPromotion().getName()
                )

                .groupId(
                        enrollment.getGroup().getId()
                )

                .groupName(
                        enrollment.getGroup().getName()
                )

                .enrollmentDate(
                        enrollment.getEnrollmentDate()
                )

                .status(
                        enrollment.getStatus()
                )

                .createdAt(
                        enrollment.getCreatedAt()
                )

                .build();
    }
}
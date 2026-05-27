package com.unchk.backend.enrollments.service;

import com.unchk.backend.enrollments.dto.EnrollmentRequestDTO;
import com.unchk.backend.enrollments.dto.EnrollmentResponseDTO;
import com.unchk.backend.enrollments.entity.Enrollment;
import com.unchk.backend.enrollments.entity.EnrollmentStatus;
import com.unchk.backend.enrollments.repository.EnrollmentRepository;
import com.unchk.backend.formations.entity.Formation;
import com.unchk.backend.formations.repository.FormationRepository;
import com.unchk.backend.students.entity.Student;
import com.unchk.backend.students.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    private final StudentRepository studentRepository;

    private final FormationRepository formationRepository;

    /**
     * Création inscription
     */
    public EnrollmentResponseDTO createEnrollment(EnrollmentRequestDTO request) {

        // Recherche étudiant
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Recherche formation
        Formation formation = formationRepository.findById(request.getFormationId())
                .orElseThrow(() -> new RuntimeException("Formation not found"));

        // Création inscription
        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .formation(formation)
                .enrollmentDate(LocalDate.now())
                .academicYear(request.getAcademicYear())
                .status(EnrollmentStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .build();

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        return mapToResponse(savedEnrollment);
    }

    /**
     * Liste inscriptions
     */
    public List<EnrollmentResponseDTO> getAllEnrollments() {

        return enrollmentRepository.findAll()

                .stream()

                .map(this::mapToResponse)

                .collect(Collectors.toList());
    }

    /**
     * Mapping entity -> DTO
     */
    private  EnrollmentResponseDTO mapToResponse(Enrollment enrollment) {

        return EnrollmentResponseDTO.builder()

                .id(enrollment.getId())

                .studentName(
                        enrollment.getStudent()
                                .getFirstName()
                        + " " +

                                enrollment.getStudent()
                                        .getLastName()
                )

                .matricule(
                        enrollment.getStudent()
                                .getMatricule()
                )

                .formationName(
                        enrollment.getFormation()
                                .getName()
                )

                .formationCode(
                        enrollment.getFormation()
                                .getCode()
                )

                .enrollmentDate(
                        enrollment.getEnrollmentDate()
                )

                .academicYear(
                        enrollment.getAcademicYear()
                )

                .status(
                        enrollment.getStatus()
                )

                .createdAt(
                        enrollment.getCreatedAt()
                )

                .build();
    }

    /**
     * Mise à jour inscription
     */
    public EnrollmentResponseDTO updateEnrollment(
            Long id, EnrollmentRequestDTO request
    ) {

        // Recherche inscription
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() ->

                        new  RuntimeException("Enrollment not found")
                );

        // Recherche étudiant
        Student student = studentRepository.findById(
                request.getStudentId()
            )

                .orElseThrow(() ->

                    new RuntimeException("Etudaint introuvable")
                );

        // Recherche formation
        Formation formation = formationRepository.findById(
                request.getFormationId()
        )

                .orElseThrow(() ->

                    new RuntimeException("Formation introuvable")
                );

        // Mise à jour
        enrollment.setStudent(student);

        enrollment.setFormation(formation);

        enrollment.setAcademicYear(request.getAcademicYear());

        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);

        return mapToResponse(updatedEnrollment);
    }

    /**
     * Suppression inscription
     */
    public void deleteEnrollment(Long id) {

        Enrollment enrollment =

                enrollmentRepository.findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Inscription introuvable"
                                )
                        );

        enrollmentRepository.delete(enrollment);
    }
}

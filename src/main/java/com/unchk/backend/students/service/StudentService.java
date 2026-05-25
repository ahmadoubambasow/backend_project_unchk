package com.unchk.backend.students.service;

import com.unchk.backend.students.dto.*;
import com.unchk.backend.students.entity.*;

import com.unchk.backend.students.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service étudiants.
 */
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    /**
     * Création étudiant.
     */
    public StudentResponseDTO createStudent(
            StudentRequestDTO request
    ) {

        // Vérifie email existant
        studentRepository.findByEmail(
                request.getEmail()
        ).ifPresent(student -> {

            throw new RuntimeException(
                    "Email déjà utilisé"
            );
        });

        // Création étudiant
        Student student = Student.builder()

                .matricule(generateMatricule())

                .firstName(request.getFirstName())

                .lastName(request.getLastName())

                .email(request.getEmail())

                .phone(request.getPhone())

                .gender(request.getGender())

                .birthDate(request.getBirthDate())

                .address(request.getAddress())

                .status(StudentStatus.ACTIVE)

                .createdAt(LocalDateTime.now())

                .build();

        Student savedStudent =
                studentRepository.save(student);

        return mapToResponse(savedStudent);
    }

    /**
     * Liste étudiants.
     */
    public List<StudentResponseDTO> getAllStudents() {

        return studentRepository.findAll()

                .stream()

                .map(this::mapToResponse)

                .collect(Collectors.toList());
    }

    /**
     * Conversion entity → DTO.
     */
    private StudentResponseDTO mapToResponse(
            Student student
    ) {

        return StudentResponseDTO.builder()

                .id(student.getId())

                .matricule(student.getMatricule())

                .firstName(student.getFirstName())

                .lastName(student.getLastName())

                .email(student.getEmail())

                .phone(student.getPhone())

                .gender(student.getGender())

                .birthDate(student.getBirthDate())

                .address(student.getAddress())

                .status(student.getStatus())

                .createdAt(student.getCreatedAt())

                .build();
    }

    /**
     * Générer matricule étudiant
     */
    private String generateMatricule() {

        long count = studentRepository.count() +1;

        return String.format(
                "STD2026%03d",
                count
        );
    }

    /**
     * Supression étudiant
     */
    public void deleteStudent(Long id) {

        // Vérifier existence
        Student student = studentRepository.findById(id)
                .orElseThrow(() ->

                    new RuntimeException("Etudiant introuvable")
                );

        // SUppression
        studentRepository.delete(student);
    }

    /**
     * Mise à jour étudiant.
     */
    public StudentResponseDTO updateStudent(

            Long id,

            StudentRequestDTO request
    ) {

        // Recherche étudiant
        Student student =
                studentRepository.findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Étudiant introuvable"
                                )
                        );

        // Mise à jour
        student.setFirstName(
                request.getFirstName()
        );

        student.setLastName(
                request.getLastName()
        );

        student.setEmail(
                request.getEmail()
        );

        student.setPhone(
                request.getPhone()
        );

        student.setGender(
                request.getGender()
        );

        student.setBirthDate(
                request.getBirthDate()
        );

        student.setAddress(
                request.getAddress()
        );

        // Sauvegarde
        Student updatedStudent =
                studentRepository.save(student);

        return mapToResponse(updatedStudent);
    }
}

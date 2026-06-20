package com.unchk.backend.hr.service;

import com.unchk.backend.administration.service.DocumentStorageService;
import com.unchk.backend.hr.dto.StudentFileRequestDTO;
import com.unchk.backend.hr.dto.StudentFileResponseDTO;
import com.unchk.backend.hr.entity.StudentFile;
import com.unchk.backend.hr.repository.StudentFileRepository;
import com.unchk.backend.students.entity.Student;
import com.unchk.backend.students.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentFileService {

    private final StudentFileRepository
            repository;

    private final StudentRepository
            studentRepository;

    private final DocumentStorageService
            documentStorageService;

    /**
     * Création
     */
    public StudentFileResponseDTO create(
            StudentFileRequestDTO request
    ) {

        if (request.getStudentId() == null) {

            throw new RuntimeException(
                    "Étudiant obligatoire"
            );
        }

        if (
                repository.findByStudent_Id(
                        request.getStudentId()
                ).isPresent()
        ) {

            throw new RuntimeException(
                    "Cet étudiant possède déjà un dossier"
            );
        }

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

        StudentFile studentFile =

                StudentFile.builder()

                        .student(student)

                        .guardianName(
                                request.getGuardianName()
                        )

                        .guardianPhone(
                                request.getGuardianPhone()
                        )

                        .address(
                                request.getAddress()
                        )

                        .previousSchool(
                                request.getPreviousSchool()
                        )

                        .birthCertificatePath(
                                request.getBirthCertificatePath()
                        )

                        .diplomaPath(
                                request.getDiplomaPath()
                        )

                        .photoPath(
                                request.getPhotoPath()
                        )

                        .remarks(
                                request.getRemarks()
                        )

                        .build();

        studentFile =
                repository.save(
                        studentFile
                );

        studentFile.setRegistrationNumber(

                String.format(
                        "ETU-%d-%04d",
                        java.time.Year.now().getValue(),
                        studentFile.getId()
                )
        );

        studentFile =
                repository.save(
                        studentFile
                );

        return mapToResponse(
                studentFile
        );
    }

    /**
     * Modification
     */
    public StudentFileResponseDTO update(

            Long id,

            StudentFileRequestDTO request

    ) {

        StudentFile studentFile =

                repository

                        .findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Dossier introuvable"
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

        studentFile.setStudent(
                student
        );

        studentFile.setGuardianName(
                request.getGuardianName()
        );

        studentFile.setGuardianPhone(
                request.getGuardianPhone()
        );

        studentFile.setAddress(
                request.getAddress()
        );

        studentFile.setPreviousSchool(
                request.getPreviousSchool()
        );

        studentFile.setBirthCertificatePath(
                request.getBirthCertificatePath()
        );

        studentFile.setDiplomaPath(
                request.getDiplomaPath()
        );

        studentFile.setPhotoPath(
                request.getPhotoPath()
        );

        studentFile.setRemarks(
                request.getRemarks()
        );

        studentFile =
                repository.save(
                        studentFile
                );

        return mapToResponse(
                studentFile
        );
    }

    /**
     * Liste
     */
    public List<StudentFileResponseDTO>
    getAll() {

        return repository

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
    public StudentFileResponseDTO getById(
            Long id
    ) {

        StudentFile studentFile =

                repository

                        .findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Dossier introuvable"
                                )
                        );

        return mapToResponse(
                studentFile
        );
    }

    /**
     * Suppression
     */
    public void delete(
            Long id
    ) {

        repository.deleteById(id);
    }

    /**
     * Upload
     */
    public String uploadDocument(
            MultipartFile file
    ) {

        try {

            return documentStorageService
                    .storeDocument(file);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erreur upload fichier : "
                            + e.getMessage()
            );
        }
    }

    /**
     * Mapping
     */
    private StudentFileResponseDTO mapToResponse(
            StudentFile studentFile
    ) {

        String fullName = null;
        String email = null;

        if (studentFile.getStudent() != null
                && studentFile.getStudent().getUser() != null) {

            fullName =
                    studentFile.getStudent()
                            .getUser()
                            .getFullName();

            email =
                    studentFile.getStudent()
                            .getUser()
                            .getEmail();
        }


        return StudentFileResponseDTO

                .builder()

                .id(
                        studentFile.getId()
                )

                .studentId(
                        studentFile.getStudent().getId()
                )

                .fullName(
                        fullName
                        )

                .email(
                        email
                )

                .registrationNumber(
                        studentFile.getRegistrationNumber()
                )

                .guardianName(
                        studentFile.getGuardianName()
                )

                .guardianPhone(
                        studentFile.getGuardianPhone()
                )

                .address(
                        studentFile.getAddress()
                )

                .previousSchool(
                        studentFile.getPreviousSchool()
                )

                .birthCertificatePath(
                        studentFile.getBirthCertificatePath()
                )

                .diplomaPath(
                        studentFile.getDiplomaPath()
                )

                .photoPath(
                        studentFile.getPhotoPath()
                )

                .remarks(
                        studentFile.getRemarks()
                )

                .build();
    }
}
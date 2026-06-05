package com.unchk.backend.insertion.service;

import com.unchk.backend.insertion.dto.StudentContactRequestDTO;
import com.unchk.backend.insertion.dto.StudentContactResponseDTO;
import com.unchk.backend.insertion.entity.StudentContact;
import com.unchk.backend.insertion.repository.StudentContactRepository;
import com.unchk.backend.students.entity.Student;
import com.unchk.backend.students.repository.StudentRepository;
import com.unchk.backend.users.entity.User;
import com.unchk.backend.users.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentContactService {

    private final StudentContactRepository
            studentContactRepository;

    private final StudentRepository
            studentRepository;

    private final UserRepository
            userRepository;

    /**
     * Création
     */
    public StudentContactResponseDTO create(

            StudentContactRequestDTO request

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

        User createdBy =

                userRepository

                        .findById(
                                request.getCreatedById()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Utilisateur introuvable"
                                )
                        );

        StudentContact contact =

                StudentContact.builder()

                        .student(
                                student
                        )

                        .createdBy(
                                createdBy
                        )

                        .contactDate(
                                request.getContactDate()
                        )

                        .contactType(
                                request.getContactType()
                        )

                        .subject(
                                request.getSubject()
                        )

                        .description(
                                request.getDescription()
                        )

                        .build();

        contact = studentContactRepository.save(
                contact
        );

        return mapToResponse(
                contact
        );
    }

    /**
     * Modification
     */
    public StudentContactResponseDTO update(

            Long id,

            StudentContactRequestDTO request

    ) {

        StudentContact contact =

                studentContactRepository

                        .findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Contact introuvable"
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

        User createdBy =

                userRepository

                        .findById(
                                request.getCreatedById()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Utilisateur introuvable"
                                )
                        );

        contact.setStudent(
                student
        );

        contact.setCreatedBy(
                createdBy
        );

        contact.setContactDate(
                request.getContactDate()
        );

        contact.setContactType(
                request.getContactType()
        );

        contact.setSubject(
                request.getSubject()
        );

        contact.setDescription(
                request.getDescription()
        );

        contact = studentContactRepository.save(
                contact
        );

        return mapToResponse(
                contact
        );
    }

    /**
     * Liste complète
     */
    public List<StudentContactResponseDTO>
    getAll() {

        return studentContactRepository

                .findAll()

                .stream()

                .map(
                        this::mapToResponse
                )

                .toList();
    }

    /**
     * Contacts d'un étudiant
     */
    public List<StudentContactResponseDTO>
    getStudentContacts(

            Long studentId

    ) {

        return studentContactRepository

                .findByStudentIdOrderByContactDateDesc(
                        studentId
                )

                .stream()

                .map(
                        this::mapToResponse
                )

                .toList();
    }

    /**
     * Détail
     */
    public StudentContactResponseDTO
    getById(

            Long id

    ) {

        StudentContact contact =

                studentContactRepository

                        .findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Contact introuvable"
                                )
                        );

        return mapToResponse(
                contact
        );
    }

    /**
     * Suppression
     */
    public void delete(

            Long id

    ) {

        studentContactRepository.deleteById(
                id
        );
    }

    /**
     * Mapping DTO
     */
    private StudentContactResponseDTO
    mapToResponse(

            StudentContact contact

    ) {

        return StudentContactResponseDTO

                .builder()

                .id(
                        contact.getId()
                )

                .studentId(
                        contact.getStudent().getId()
                )

                .studentName(

                        contact.getStudent()
                                .getFirstName()

                                +

                                " "

                                +

                                contact.getStudent()
                                        .getLastName()
                )

                .createdById(
                        contact.getCreatedBy().getId()
                )

                .createdByName(
                        contact.getCreatedBy().getFullName()
                )

                .contactDate(
                        contact.getContactDate()
                )

                .contactType(
                        contact.getContactType()
                )

                .subject(
                        contact.getSubject()
                )

                .description(
                        contact.getDescription()
                )

                .build();
    }
}
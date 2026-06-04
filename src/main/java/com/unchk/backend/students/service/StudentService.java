package com.unchk.backend.students.service;

import com.unchk.backend.formations.entity.Formation;
import com.unchk.backend.formations.repository.FormationRepository;
import com.unchk.backend.students.dto.StudentRequestDTO;
import com.unchk.backend.students.dto.StudentResponseDTO;
import com.unchk.backend.students.entity.Student;
import com.unchk.backend.students.entity.StudentGroup;
import com.unchk.backend.students.repository.StudentGroupRepository;
import com.unchk.backend.students.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository
            studentRepository;

    private final FormationRepository
            formationRepository;

    private final StudentGroupRepository studentGroupRepository;

    /**
     * Création
     */
    public StudentResponseDTO
    createStudent(

            StudentRequestDTO request

    ) {

        StudentGroup group =

                studentGroupRepository

                        .findById(
                                request.getGroupId()
                        )

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

        Student student =

                Student.builder()

                        .ine(
                                generateIne(request.getStartYear())
                        )

                        .firstName(
                                request.getFirstName()
                        )

                        .lastName(
                                request.getLastName()
                        )

                        .birthDate(
                                request.getBirthDate()
                        )


                        .startYear(
                                request.getStartYear()
                        )

                        .graduationYear(
                                request.getGraduationYear()
                        )

                        .diplomas(
                                request.getDiplomas()
                        )

                        .otherTrainings(
                                request.getOtherTrainings()
                        )

                        .formation(
                                formation
                        )

                        .group(
                                group
                        )

                        .build();

        student = studentRepository.save(
                student
        );

        return mapToResponse(
                student
        );
    }

    /**
     * Liste
     */
    public List<StudentResponseDTO>
    getAllStudents() {

        return studentRepository

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
    public StudentResponseDTO
    getStudentById(

            Long id

    ) {

        Student student =

                studentRepository

                        .findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Étudiant introuvable"
                                )
                        );

        return mapToResponse(
                student
        );
    }

    /**
     * Liste par formation
     */
    public List<StudentResponseDTO>
    getStudentsByFormation(

            Long formationId

    ) {

        return studentRepository

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
    public StudentResponseDTO
    updateStudent(

            Long id,

            StudentRequestDTO request

    ) {

        StudentGroup group =

                studentGroupRepository

                        .findById(
                                request.getGroupId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Groupe introuvable"
                                )
                        );

        Student student =

                studentRepository

                        .findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Étudiant introuvable"
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

        student.setFirstName(
                request.getFirstName()
        );

        student.setLastName(
                request.getLastName()
        );

        student.setBirthDate(
                request.getBirthDate()
        );


        student.setStartYear(
                request.getStartYear()
        );

        student.setGraduationYear(
                request.getGraduationYear()
        );

        student.setDiplomas(
                request.getDiplomas()
        );

        student.setOtherTrainings(
                request.getOtherTrainings()
        );

        student.setFormation(
                formation
        );

        student.setGroup(group);

        student = studentRepository.save(
                student
        );

        return mapToResponse(
                student
        );
    }

    /**
     * Suppression
     */
    public void deleteStudent(

            Long id

    ) {

        Student student =

                studentRepository

                        .findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Étudiant introuvable"
                                )
                        );

        studentRepository.delete(
                student
        );
    }

    private String generateIne(

            Integer startYear

    ) {

        long count =

                studentRepository

                        .countByStartYear(
                                startYear
                        );

        return String.format(

                "UNCHK-%d-%04d",

                startYear,

                count + 1
        );
    }

    /**
     * Mapping DTO
     */
    private StudentResponseDTO
    mapToResponse(

            Student student

    ) {

        return StudentResponseDTO

                .builder()

                .id(
                        student.getId()
                )

                .ine(
                        student.getIne()
                )

                .firstName(
                        student.getFirstName()
                )

                .lastName(
                        student.getLastName()
                )

                .promotion(
                        student.getPromotion()
                )

                .startYear(
                        student.getStartYear()
                )

                .graduationYear(
                        student.getGraduationYear()
                )

                .birthDate(
                        student.getBirthDate()
                )

                .diplomas(
                        student.getDiplomas()
                )

                .otherTrainings(
                        student.getOtherTrainings()
                )

                .formationName(
                        student.getFormation()
                                .getName()
                )

                .formationId(
                        student.getFormation().getId()
                )

                .groupId(

                        student.getGroup() != null

                                ?

                                student.getGroup().getId()

                                :

                                null
                )

                .groupName(

                        student.getGroup() != null

                                ?

                                student.getGroup().getName()

                                :

                                null
                )

                .build();
    }
}
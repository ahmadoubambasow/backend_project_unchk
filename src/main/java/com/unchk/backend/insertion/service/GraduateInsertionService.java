package com.unchk.backend.insertion.service;

import com.unchk.backend.insertion.dto.*;
import com.unchk.backend.insertion.entity.*;
import com.unchk.backend.insertion.repository.GraduateInsertionRepository;
import com.unchk.backend.students.entity.Student;
import com.unchk.backend.students.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GraduateInsertionService {

    private final GraduateInsertionRepository
            repository;

    private final StudentRepository
            studentRepository;

    public GraduateInsertionResponseDTO create(
            GraduateInsertionRequestDTO request
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

        GraduateInsertion insertion =

                GraduateInsertion.builder()

                        .student(student)

                        .status(
                                request.getStatus()
                        )

                        .company(
                                request.getCompany()
                        )

                        .position(
                                request.getPosition()
                        )

                        .startDate(
                                request.getStartDate()
                        )

                        .salary(
                                request.getSalary()
                        )

                        .remarks(
                                request.getRemarks()
                        )

                        .build();

        return mapToResponse(
                repository.save(
                        insertion
                )
        );
    }

    public GraduateInsertionResponseDTO update(
            Long id,
            GraduateInsertionRequestDTO request
    ) {

        GraduateInsertion insertion =

                repository

                        .findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Insertion introuvable"
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

        insertion.setStudent(student);
        insertion.setStatus(request.getStatus());
        insertion.setCompany(request.getCompany());
        insertion.setPosition(request.getPosition());
        insertion.setStartDate(request.getStartDate());
        insertion.setSalary(request.getSalary());
        insertion.setRemarks(request.getRemarks());

        return mapToResponse(
                repository.save(
                        insertion
                )
        );
    }

    public List<GraduateInsertionResponseDTO>
    getAll() {

        return repository

                .findAll()

                .stream()

                .map(
                        this::mapToResponse
                )

                .toList();
    }

    public GraduateInsertionResponseDTO
    getById(Long id) {

        return mapToResponse(

                repository

                        .findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Insertion introuvable"
                                )
                        )
        );
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }

    private GraduateInsertionResponseDTO
    mapToResponse(
            GraduateInsertion insertion
    ) {

        return GraduateInsertionResponseDTO

                .builder()

                .id(
                        insertion.getId()
                )

                .studentId(
                        insertion.getStudent().getId()
                )

                .studentName(
                        insertion.getStudent() != null
                                && insertion.getStudent().getUser() != null
                                ? insertion.getStudent()
                                .getUser()
                                .getFullName()
                                : "Étudiant non associé"
                )

                .status(
                        insertion.getStatus()
                )

                .company(
                        insertion.getCompany()
                )

                .position(
                        insertion.getPosition()
                )

                .startDate(
                        insertion.getStartDate()
                )

                .salary(
                        insertion.getSalary()
                )

                .remarks(
                        insertion.getRemarks()
                )

                .build();
    }
}
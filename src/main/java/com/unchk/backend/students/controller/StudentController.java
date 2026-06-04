package com.unchk.backend.students.controller;

import com.unchk.backend.students.dto.StudentRequestDTO;
import com.unchk.backend.students.dto.StudentResponseDTO;
import com.unchk.backend.students.service.StudentService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService
            studentService;

    @PostMapping
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
    )
    public StudentResponseDTO
    createStudent(

            @RequestBody
            StudentRequestDTO request

    ) {

        return studentService.createStudent(
                request
        );
    }

    @GetMapping
    public List<StudentResponseDTO>
    getAllStudents() {

        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentResponseDTO
    getStudentById(

            @PathVariable
            Long id

    ) {

        return studentService
                .getStudentById(id);
    }

    @GetMapping(
            "/formation/{formationId}"
    )
    public List<StudentResponseDTO>
    getStudentsByFormation(

            @PathVariable
            Long formationId

    ) {

        return studentService

                .getStudentsByFormation(
                        formationId
                );
    }

    @PutMapping("/{id}")
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
    )
    public StudentResponseDTO
    updateStudent(

            @PathVariable
            Long id,

            @RequestBody
            StudentRequestDTO request

    ) {

        return studentService.updateStudent(
                id,
                request
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(
            "hasRole('ADMIN')"
    )
    public void deleteStudent(

            @PathVariable
            Long id

    ) {

        studentService.deleteStudent(
                id
        );
    }
}
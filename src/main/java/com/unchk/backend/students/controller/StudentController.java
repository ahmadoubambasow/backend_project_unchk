package com.unchk.backend.students.controller;

import com.unchk.backend.students.dto.StudentRequestDTO;
import com.unchk.backend.students.dto.StudentResponseDTO;
import com.unchk.backend.students.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST étudiants
 */
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    /**
     * Création étudiant
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public StudentResponseDTO createStudent(
            @Valid
            @RequestBody
            StudentRequestDTO request
    ) {
        return studentService.createStudent(request);
    }

    /**
     * Liste étudiants
     */
    @GetMapping
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getAllStudents();
    }
}

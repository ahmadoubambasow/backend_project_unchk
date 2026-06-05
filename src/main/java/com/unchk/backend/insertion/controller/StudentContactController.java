package com.unchk.backend.insertion.controller;

import com.unchk.backend.insertion.dto.StudentContactRequestDTO;
import com.unchk.backend.insertion.dto.StudentContactResponseDTO;
import com.unchk.backend.insertion.service.StudentContactService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student-contacts")
@RequiredArgsConstructor
public class StudentContactController {

    private final StudentContactService
            service;

    /**
     * Création
     */
    @PostMapping
    public StudentContactResponseDTO create(

            @RequestBody
            StudentContactRequestDTO request

    ) {

        return service.create(
                request
        );
    }

    /**
     * Liste complète
     */
    @GetMapping
    public List<StudentContactResponseDTO>
    getAll() {

        return service.getAll();
    }

    /**
     * Détail
     */
    @GetMapping("/{id}")
    public StudentContactResponseDTO
    getById(

            @PathVariable
            Long id

    ) {

        return service.getById(
                id
        );
    }

    /**
     * Contacts d'un étudiant
     */
    @GetMapping("/student/{studentId}")
    public List<StudentContactResponseDTO>
    getStudentContacts(

            @PathVariable
            Long studentId

    ) {

        return service.getStudentContacts(
                studentId
        );
    }

    /**
     * Modification
     */
    @PutMapping("/{id}")
    public StudentContactResponseDTO update(

            @PathVariable
            Long id,

            @RequestBody
            StudentContactRequestDTO request

    ) {

        return service.update(
                id,
                request
        );
    }

    /**
     * Suppression
     */
    @DeleteMapping("/{id}")
    public void delete(

            @PathVariable
            Long id

    ) {

        service.delete(id);
    }
}
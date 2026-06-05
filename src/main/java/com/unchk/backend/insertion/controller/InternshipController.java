package com.unchk.backend.insertion.controller;

import com.unchk.backend.insertion.dto.InternshipRequestDTO;
import com.unchk.backend.insertion.dto.InternshipResponseDTO;
import com.unchk.backend.insertion.service.InternshipService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/internships")
@RequiredArgsConstructor
public class InternshipController {

    private final InternshipService
            service;

    @PostMapping
    public InternshipResponseDTO create(

            @RequestBody
            InternshipRequestDTO request

    ) {

        return service.create(
                request
        );
    }

    @GetMapping
    public List<InternshipResponseDTO>
    getAll() {

        return service.getAll();
    }

    @GetMapping("/{id}")
    public InternshipResponseDTO
    getById(

            @PathVariable
            Long id

    ) {

        return service.getById(
                id
        );
    }

    @PutMapping("/{id}")
    public InternshipResponseDTO update(

            @PathVariable
            Long id,

            @RequestBody
            InternshipRequestDTO request

    ) {

        return service.update(
                id,
                request
        );
    }

    @DeleteMapping("/{id}")
    public void delete(

            @PathVariable
            Long id

    ) {

        service.delete(id);
    }
}
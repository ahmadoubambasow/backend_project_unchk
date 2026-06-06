package com.unchk.backend.insertion.controller;

import com.unchk.backend.insertion.dto.*;
import com.unchk.backend.insertion.service.GraduateInsertionService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/graduate-insertions")
@RequiredArgsConstructor
public class GraduateInsertionController {

    private final GraduateInsertionService
            service;

    @PostMapping
    public GraduateInsertionResponseDTO create(

            @RequestBody
            GraduateInsertionRequestDTO request

    ) {

        return service.create(request);
    }

    @GetMapping
    public List<GraduateInsertionResponseDTO>
    getAll() {

        return service.getAll();
    }

    @GetMapping("/{id}")
    public GraduateInsertionResponseDTO
    getById(

            @PathVariable
            Long id

    ) {

        return service.getById(id);
    }

    @PutMapping("/{id}")
    public GraduateInsertionResponseDTO
    update(

            @PathVariable
            Long id,

            @RequestBody
            GraduateInsertionRequestDTO request

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
package com.unchk.backend.insertion.controller;

import com.unchk.backend.insertion.dto.*;
import com.unchk.backend.insertion.service.PartnerService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partners")
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerService service;

    @PostMapping
    public PartnerResponseDTO create(
            @RequestBody
            PartnerRequestDTO request
    ) {

        return service.create(
                request
        );
    }

    @GetMapping
    public List<PartnerResponseDTO> getAll() {

        return service.getAll();
    }

    @GetMapping("/{id}")
    public PartnerResponseDTO getById(
            @PathVariable Long id
    ) {

        return service.getById(id);
    }

    @PutMapping("/{id}")
    public PartnerResponseDTO update(

            @PathVariable Long id,

            @RequestBody
            PartnerRequestDTO request

    ) {

        return service.update(
                id,
                request
        );
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ) {

        service.delete(id);
    }
}
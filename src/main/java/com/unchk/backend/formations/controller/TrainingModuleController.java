package com.unchk.backend.formations.controller;

import com.unchk.backend.formations.dto.TrainingModuleRequestDTO;
import com.unchk.backend.formations.dto.TrainingModuleResponseDTO;
import com.unchk.backend.formations.service.TrainingModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training-modules")
@RequiredArgsConstructor
public class TrainingModuleController {

    private final TrainingModuleService
            service;

    @GetMapping
    public List<TrainingModuleResponseDTO>
    getAllModules() {

        return service.getAllModules();
    }

    @PostMapping
    public TrainingModuleResponseDTO
    createModule(

            @RequestBody
            TrainingModuleRequestDTO request

    ) {

        return service.createModule(
                request
        );
    }

    @GetMapping(
            "/formation/{formationId}"
    )
    public List<TrainingModuleResponseDTO>
    getFormationModules(

            @PathVariable
            Long formationId

    ) {

        return service
                .getFormationModules(
                        formationId
                );
    }

    @PutMapping("/{id}")
    public TrainingModuleResponseDTO
    updateModule(

            @PathVariable
            Long id,

            @RequestBody
            TrainingModuleRequestDTO request

    ) {

        return service.updateModule(
                id,
                request
        );
    }

    @GetMapping("/{id}")
    public TrainingModuleResponseDTO
    getModuleById(

            @PathVariable
            Long id

    ) {

        return service.getModuleById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteModule(

            @PathVariable
            Long id

    ) {

        service.deleteModule(id);
    }
}
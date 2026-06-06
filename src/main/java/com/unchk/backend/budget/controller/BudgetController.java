package com.unchk.backend.budget.controller;

import com.unchk.backend.budget.dto.BudgetRequestDTO;
import com.unchk.backend.budget.dto.BudgetResponseDTO;
import com.unchk.backend.budget.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService service;

    @PostMapping
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
    )
    public BudgetResponseDTO create(
            @RequestBody BudgetRequestDTO request
    ) {
        return service.create(request);
    }

    @GetMapping
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
    )
    public List<BudgetResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
    )
    public BudgetResponseDTO getById(
            @PathVariable Long id
    ) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
    )
    public BudgetResponseDTO update(
            @PathVariable Long id,
            @RequestBody BudgetRequestDTO request
    ) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
    )
    public void delete(
            @PathVariable Long id
    ) {
        service.delete(id);
    }

    @PostMapping("/upload")
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
    )
    public ResponseEntity<Map<String, String>>
    uploadDocument(

            @RequestParam("file")
            MultipartFile file

    ) {

        String filePath =

                service.uploadDocument(
                        file
                );

        return ResponseEntity.ok(

                Map.of(
                        "filePath",
                        filePath
                )
        );
    }
}
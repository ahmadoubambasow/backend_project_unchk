package com.unchk.backend.hr.controller;

import com.unchk.backend.hr.dto.PersonnelFileRequestDTO;
import com.unchk.backend.hr.dto.PersonnelFileResponseDTO;
import com.unchk.backend.hr.service.PersonnelFileService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/personnel-files")
@RequiredArgsConstructor
public class PersonnelFileController {

    private final PersonnelFileService
            service;

    @PostMapping
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
    )
    public PersonnelFileResponseDTO create(

            @RequestBody
            PersonnelFileRequestDTO request

    ) {

        return service.create(
                request
        );
    }

    @GetMapping
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
    )
    public List<PersonnelFileResponseDTO>
    getAll() {

        return service.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
    )
    public PersonnelFileResponseDTO getById(

            @PathVariable Long id

    ) {

        return service.getById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
    )
    public PersonnelFileResponseDTO update(

            @PathVariable Long id,

            @RequestBody
            PersonnelFileRequestDTO request

    ) {

        return service.update(
                id,
                request
        );
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
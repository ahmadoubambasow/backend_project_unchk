package com.unchk.backend.groups.controller;

import com.unchk.backend.groups.dto.StudentGroupRequestDTO;
import com.unchk.backend.groups.dto.StudentGroupResponseDTO;
import com.unchk.backend.groups.service.StudentGroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class StudentGroupController {

    private final StudentGroupService studentGroupService;

    /**
     * Création groupe.
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN', 'SUPER_ADMIN')"
    )
    @PostMapping
    public StudentGroupResponseDTO createGroup(

            @Valid
            @RequestBody
            StudentGroupRequestDTO request
    ) {

        return studentGroupService.createGroup(
                request
        );
    }

    /**
     * Liste de tous les groupes.
     */
    @GetMapping
    public List<StudentGroupResponseDTO>
    getAllGroups() {

        return studentGroupService.getAllGroups();
    }

    /**
     * Liste des groupes d'une formation.
     */
    @GetMapping("/formation/{formationId}")
    public List<StudentGroupResponseDTO>
    getGroupsByFormation(

            @PathVariable
            Long formationId
    ) {

        return studentGroupService.getGroupsByFormation(
                formationId
        );
    }

    /**
     * Mise à jour groupe.
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN', 'SUPER_ADMIN')"
    )
    @PutMapping("/{id}")
    public StudentGroupResponseDTO updateGroup(

            @PathVariable
            Long id,

            @Valid
            @RequestBody
            StudentGroupRequestDTO request
    ) {

        return studentGroupService.updateGroup(
                id,
                request
        );
    }

    /**
     * Suppression groupe.
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN', 'SUPER_ADMIN')"
    )
    @DeleteMapping("/{id}")
    public void deleteGroup(

            @PathVariable
            Long id
    ) {

        studentGroupService.deleteGroup(
                id
        );
    }
}

package com.unchk.backend.students.controller;

import com.unchk.backend.students.dto.StudentGroupRequestDTO;
import com.unchk.backend.students.dto.StudentGroupResponseDTO;
import com.unchk.backend.students.service.StudentGroupService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student-groups")
@RequiredArgsConstructor
public class StudentGroupController {

    private final StudentGroupService
            studentGroupService;

    @PostMapping
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
    )
    public StudentGroupResponseDTO
    createGroup(

            @RequestBody
            StudentGroupRequestDTO request

    ) {

        return studentGroupService
                .createGroup(request);
    }

    @GetMapping
    public List<StudentGroupResponseDTO>
    getAllGroups() {

        return studentGroupService
                .getAllGroups();
    }

    @GetMapping("/{id}")
    public StudentGroupResponseDTO
    getGroupById(

            @PathVariable
            Long id

    ) {

        return studentGroupService
                .getGroupById(id);
    }

    @GetMapping(
            "/formation/{formationId}"
    )
    public List<StudentGroupResponseDTO>
    getGroupsByFormation(

            @PathVariable
            Long formationId

    ) {

        return studentGroupService
                .getGroupsByFormation(
                        formationId
                );
    }

    @PutMapping("/{id}")
    @PreAuthorize(
            "hasAnyRole('ADMIN','RESPONSABLE_FORMATION')"
    )
    public StudentGroupResponseDTO
    updateGroup(

            @PathVariable
            Long id,

            @RequestBody
            StudentGroupRequestDTO request

    ) {

        return studentGroupService
                .updateGroup(
                        id,
                        request
                );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(
            "hasRole('ADMIN')"
    )
    public void deleteGroup(

            @PathVariable
            Long id

    ) {

        studentGroupService.deleteGroup(
                id
        );
    }
}
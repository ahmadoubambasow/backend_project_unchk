package com.unchk.backend.users.controller;

import com.unchk.backend.users.dto.RoleResponseDTO;
import com.unchk.backend.users.service.RoleService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    /**
     * Liste des rôles
     */
    @GetMapping
    public List<RoleResponseDTO> getAllRoles() {

        return roleService.getAllRoles();
    }
}
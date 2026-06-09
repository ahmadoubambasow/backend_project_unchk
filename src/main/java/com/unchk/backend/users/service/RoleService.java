package com.unchk.backend.users.service;

import com.unchk.backend.users.dto.RoleResponseDTO;
import com.unchk.backend.users.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    /**
     * Liste des rôles
     */
    public List<RoleResponseDTO> getAllRoles() {

        return roleRepository.findAll()

                .stream()

                .map(role ->

                        RoleResponseDTO.builder()

                                .id(role.getId())

                                .name(role.getName().name())

                                .build()
                )

                .toList();
    }
}
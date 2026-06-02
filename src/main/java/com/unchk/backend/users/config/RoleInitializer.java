package com.unchk.backend.users.config;

import com.unchk.backend.users.entity.Role;
import com.unchk.backend.users.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleInitializer
        implements CommandLineRunner {

    private final RoleRepository
            roleRepository;

    @Override
    public void run(
            String... args
    ) {

        createRole("ADMIN");

        createRole("ADMINISTRATIF");

        createRole("ENSEIGNANT");

        createRole("ENSEIGNANT_ASSOCIE");

        createRole("RESPONSABLE_FORMATION");

        createRole("TUTEUR");

        createRole("APPUI_INSERTION");

        createRole("ETUDIANT");
    }

    private void createRole(
            String roleName
    ) {

        if (

                !roleRepository.existsByName(
                        roleName
                )

        ) {

            roleRepository.save(

                    Role.builder()

                            .name(
                                    roleName
                            )

                            .build()
            );
        }
    }
}
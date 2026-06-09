package com.unchk.backend.users.config;

import com.unchk.backend.users.entity.Role;
import com.unchk.backend.users.entity.UserRole;
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

        for (UserRole role: UserRole.values()) {

            createRole(role);
        }
    }

    private void createRole(
            UserRole roleName
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
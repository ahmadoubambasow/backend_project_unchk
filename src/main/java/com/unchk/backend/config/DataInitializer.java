package com.unchk.backend.config;

import com.unchk.backend.users.entity.Role;
import com.unchk.backend.users.entity.UserRole;
import com.unchk.backend.users.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Initialise automatiquement les roles au démarrage
 */
@Component
@RequiredArgsConstructor

public class DataInitializer implements CommandLineRunner {

    private final RoleRepository  roleRepository;

    @Override
    public void run(String... args) {

        for (UserRole role : UserRole.values()) {

            if (!roleRepository.existsByName(role)) {

                roleRepository.save(
                        Role.builder()
                                .name(role)
                                .build()
                );
            }
        }
    }
}

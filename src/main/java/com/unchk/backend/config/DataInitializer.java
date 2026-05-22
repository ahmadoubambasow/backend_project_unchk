package com.unchk.backend.config;

import com.unchk.backend.users.entity.Role;
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

        createRoleIfNotExists("ADMIN");
        createRoleIfNotExists("ADMINISTRATIF");
        createRoleIfNotExists("ENSEIGNANT");
        createRoleIfNotExists("TUTEUR");
        createRoleIfNotExists("ETUDIANT");
        createRoleIfNotExists("INSERTION");
    }

    /**
     * Crée un role s'il n'existe pas.
     *
     * @param roleName nom role
     */
    private void createRoleIfNotExists(String roleName) {

        boolean exists = roleRepository.findByName(roleName).isPresent();

        if (!exists) {

            Role role = Role.builder()
                    .name(roleName)
                    .build();

            roleRepository.save(role);

            System.out.println("Role créé : "+ roleName);
        }
    }
}

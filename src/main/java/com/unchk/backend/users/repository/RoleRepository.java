package com.unchk.backend.users.repository;


import com.unchk.backend.users.entity.Role;
import com.unchk.backend.users.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repo... pour la gestion des roles
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * recherche un role par son nom.
     *
     * @param name nom du role
     * @return role trouvé
     */
    Optional<Role> findByName(UserRole name);

    boolean existsByName(UserRole name);
}

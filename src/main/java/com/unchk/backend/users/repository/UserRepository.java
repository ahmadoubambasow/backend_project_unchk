package com.unchk.backend.users.repository;

import com.unchk.backend.users.entity.User;
import com.unchk.backend.users.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repo... de gestion des utilisateurs
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Recherche utilisateur par email
     * @param email email utilisateur
     * @return  utilisateur trouvé
     */
    Optional<User> findByEmail(String email);

    List<User> findByRole_Name(UserRole role);

    List<User> findByRole_NameIn(List<UserRole> roles);

    long countByRole_Name(UserRole role);
}

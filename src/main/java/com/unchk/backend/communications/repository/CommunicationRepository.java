package com.unchk.backend.communications.repository;

import com.unchk.backend.communications.entity.Communication;

import com.unchk.backend.users.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunicationRepository
        extends JpaRepository<Communication, Long> {

    List<Communication> findByAccessRoleIn(
            List<UserRole> roles
    );
}
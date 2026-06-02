package com.unchk.backend.communications.repository;

import com.unchk.backend.communications.entity.Communication;

import com.unchk.backend.communications.entity.CommunicationAccessRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommunicationRepository
        extends JpaRepository<Communication, Long> {

    List<Communication> findByAccessRoleIn(
            List<CommunicationAccessRole> roles
    );
}
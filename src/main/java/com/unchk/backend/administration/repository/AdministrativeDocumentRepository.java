package com.unchk.backend.administration.repository;

import com.unchk.backend.administration.entity.AdministrativeDocument;

import com.unchk.backend.administration.entity.DocumentStatus;
import com.unchk.backend.administration.entity.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AdministrativeDocumentRepository
        extends JpaRepository<
        AdministrativeDocument,
        Long
        > {

    List<AdministrativeDocument>
    findByRecipientIdOrIssuerId(
            Long recipientId,
            Long issuerId
    );

    long countByStatus(DocumentStatus status);

    long countByType(DocumentType type);





}
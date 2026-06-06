package com.unchk.backend.administration.repository;

import com.unchk.backend.administration.entity.AdministrativeDocument;

import org.springframework.data.jpa.repository.JpaRepository;

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
}
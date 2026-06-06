package com.unchk.backend.administration.service;

import com.unchk.backend.administration.dto.AdministrativeDocumentRequestDTO;
import com.unchk.backend.administration.dto.AdministrativeDocumentResponseDTO;
import com.unchk.backend.administration.entity.AdministrativeDocument;
import com.unchk.backend.administration.entity.DocumentType;
import com.unchk.backend.administration.repository.AdministrativeDocumentRepository;
import com.unchk.backend.users.entity.User;
import com.unchk.backend.users.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdministrativeDocumentService {

    private final AdministrativeDocumentRepository
            repository;

    private final UserRepository
            userRepository;

    private final DocumentStorageService
            documentStorageService;

    /**
     * Création document
     */
    public AdministrativeDocumentResponseDTO create(

            AdministrativeDocumentRequestDTO request

    ) {

        User issuer =

                userRepository

                        .findById(
                                request.getIssuerId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Expéditeur introuvable"
                                )
                        );

        User recipient =

                userRepository

                        .findById(
                                request.getRecipientId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Destinataire introuvable"
                                )
                        );

        AdministrativeDocument document =

                AdministrativeDocument.builder()

                        .referenceNumber(
                                generateReference(
                                        request.getType()
                                )
                        )

                        .title(
                                request.getTitle()
                        )

                        .type(
                                request.getType()
                        )

                        .status(
                                request.getStatus()
                        )

                        .documentDate(
                                request.getDocumentDate()
                        )

                        .issuer(
                                issuer
                        )

                        .recipient(
                                recipient
                        )

                        .description(
                                request.getDescription()
                        )

                        .filePath(
                                request.getFilePath()
                        )

                        .build();

        document = repository.save(
                document
        );

        return mapToResponse(
                document
        );
    }

    /**
     * Modification document
     */
    public AdministrativeDocumentResponseDTO update(

            Long id,

            AdministrativeDocumentRequestDTO request

    ) {

        AdministrativeDocument document =

                repository

                        .findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Document introuvable"
                                )
                        );

        User issuer =

                userRepository

                        .findById(
                                request.getIssuerId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Expéditeur introuvable"
                                )
                        );

        User recipient =

                userRepository

                        .findById(
                                request.getRecipientId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Destinataire introuvable"
                                )
                        );

        document.setTitle(
                request.getTitle()
        );

        document.setType(
                request.getType()
        );

        document.setStatus(
                request.getStatus()
        );

        document.setDocumentDate(
                request.getDocumentDate()
        );

        document.setIssuer(
                issuer
        );

        document.setRecipient(
                recipient
        );

        document.setDescription(
                request.getDescription()
        );

        document.setFilePath(
                request.getFilePath()
        );

        document = repository.save(
                document
        );

        return mapToResponse(
                document
        );
    }

    /**
     * Liste documents
     */
    public List<AdministrativeDocumentResponseDTO>
    getAll() {

        return repository

                .findAll()

                .stream()

                .map(
                        this::mapToResponse
                )

                .toList();
    }

    /**
     * Détail document
     */
    public AdministrativeDocumentResponseDTO
    getById(

            Long id

    ) {

        AdministrativeDocument document =

                repository

                        .findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Document introuvable"
                                )
                        );

        return mapToResponse(
                document
        );
    }

    /**
     * Suppression
     */
    public void delete(

            Long id

    ) {

        repository.deleteById(id);
    }

    /**
     * Génération automatique référence
     */
    private String generateReference(

            DocumentType type

    ) {

        String prefix = switch (type) {

            case INCOMING_MAIL ->
                    "CA";

            case OUTGOING_MAIL ->
                    "CD";

            case INTERNAL_NOTE ->
                    "NI";

            case EXTERNAL_NOTE ->
                    "NE";

            case ADMINISTRATIVE_NOTE ->
                    "NA";

            case CIRCULAR ->
                    "CI";
        };

        long count =

                repository.count() + 1;

        return prefix
                + "-"
                + LocalDate.now().getYear()
                + "-"
                + String.format(
                "%04d",
                count
        );
    }

    public List<AdministrativeDocumentResponseDTO>
    getMyDocuments() {

        String email =

                SecurityContextHolder

                        .getContext()

                        .getAuthentication()

                        .getName();

        User user =

                userRepository

                        .findByEmail(email)

                        .orElseThrow();

        return repository

                .findByRecipientIdOrIssuerId(

                        user.getId(),

                        user.getId()

                )

                .stream()

                .map(this::mapToResponse)

                .toList();
    }



    /**
     * Mapping DTO
     */
    private AdministrativeDocumentResponseDTO
    mapToResponse(

            AdministrativeDocument document

    ) {

        return AdministrativeDocumentResponseDTO

                .builder()

                .id(
                        document.getId()
                )

                .referenceNumber(
                        document.getReferenceNumber()
                )

                .title(
                        document.getTitle()
                )

                .type(
                        document.getType()
                )

                .status(
                        document.getStatus()
                )

                .documentDate(
                        document.getDocumentDate()
                )

                .issuerId(

                        document.getIssuer() != null

                                ? document.getIssuer().getId()

                                : null
                )

                .issuerName(

                        document.getIssuer() != null

                                ? document.getIssuer().getFullName()

                                : "-"
                )

                .recipientId(

                        document.getRecipient() != null

                                ? document.getRecipient().getId()

                                : null
                )

                .recipientName(

                        document.getRecipient() != null

                                ? document.getRecipient().getFullName()
                                : null
                )

                .description(
                        document.getDescription()
                )

                .filePath(
                        document.getFilePath()
                )

                .build();
    }
}
package com.unchk.backend.administration.entity;

import com.unchk.backend.common.entity.BaseEntity;
import com.unchk.backend.users.entity.User;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "administrative_documents")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdministrativeDocument extends BaseEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            nullable = false,
            unique = true
    )
    private String referenceNumber;

    @Column(nullable = false)
    private String title;

    @Enumerated(
            EnumType.STRING
    )
    private DocumentType type;

    @Enumerated(
            EnumType.STRING
    )
    private DocumentStatus status;

    private LocalDate documentDate;

    @ManyToOne
    @JoinColumn(
            name = "issuer_id"
    )
    private User issuer;

    @ManyToOne
    @JoinColumn(
            name = "recipient_id"
    )
    private User recipient;

    @Column(length = 5000)
    private String description;

    private String filePath;


}
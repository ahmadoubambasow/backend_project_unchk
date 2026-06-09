package com.unchk.backend.communications.entity;

import com.unchk.backend.users.entity.UserRole;
import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "communications")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Communication {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    /**
     * Titre
     */
    @Column(nullable = false)
    private String title;

    /**
     * Type
     */
    @Enumerated(EnumType.STRING)
    private CommunicationType type;

    /**
     * Description
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Compte rendu
     */
    @Column(columnDefinition = "TEXT")
    private String report;

    /**
     * Date événement
     */
    private LocalDateTime eventDate;

    /**
     * Date création
     */
    private LocalDateTime createdAt;

    /**
     * Nom document
     */
    private String documentName;

    /**
     * URL document
     */
    private String documentUrl;

    /**
     * Type document
     */
    private String documentType;

    /**
     * Rôle autorisé
     */
    @Enumerated(EnumType.STRING)
    private UserRole accessRole;
}
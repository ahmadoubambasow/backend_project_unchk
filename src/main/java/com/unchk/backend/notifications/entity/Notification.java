package com.unchk.backend.notifications.entity;

import com.unchk.backend.communications.entity.Communication;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String message;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(
            name = "communication_id"
    )
    private Communication communication;
}
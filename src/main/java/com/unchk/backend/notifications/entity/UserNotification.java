package com.unchk.backend.notifications.entity;

import com.unchk.backend.users.entity.User;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "user_notifications")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserNotification {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;

    @ManyToOne
    @JoinColumn(
            name = "notification_id",
            nullable = false
    )
    private Notification notification;

    @Column(nullable = false)
    private Boolean isRead;
}
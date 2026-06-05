package com.unchk.backend.meetings.entity;

import com.unchk.backend.students.entity.StudentGroup;
import com.unchk.backend.users.entity.User;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "meetings")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meeting {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private String title;

    @Enumerated(
            EnumType.STRING
    )
    private MeetingType type;

    @Enumerated(
            EnumType.STRING
    )
    private MeetingStatus status;

    private LocalDate meetingDate;

    private LocalTime startTime;

    private LocalTime endTime;

    @Column(length = 5000)
    private String description;

    @Column(length = 10000)
    private String report;

    @ManyToOne
    @JoinColumn(
            name = "organizer_id"
    )
    private User organizer;

    @ManyToMany
    @JoinTable(
            name = "meeting_participants",

            joinColumns =
            @JoinColumn(
                    name = "meeting_id"
            ),

            inverseJoinColumns =
            @JoinColumn(
                    name = "user_id"
            )
    )
    private List<User> participants;

    @ManyToOne
    @JoinColumn(
            name = "group_id"
    )
    private StudentGroup group;
}
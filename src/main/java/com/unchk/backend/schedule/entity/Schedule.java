package com.unchk.backend.schedule.entity;

import com.unchk.backend.students.entity.StudentGroup;
import com.unchk.backend.formations.entity.TrainingModule;
import com.unchk.backend.users.entity.User;

import jakarta.persistence.*;

import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "schedules")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "group_id"
    )
    private StudentGroup group;

    @ManyToOne
    @JoinColumn(
            name = "module_id"
    )
    private TrainingModule trainingModule;

    @ManyToOne
    @JoinColumn(
            name = "trainer_id"
    )
    private User trainer;

    @Enumerated(
            EnumType.STRING
    )
    private DayOfWeek dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

    private String room;

    private String color;
}
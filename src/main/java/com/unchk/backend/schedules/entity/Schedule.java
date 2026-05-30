package com.unchk.backend.schedules.entity;

import com.unchk.backend.formations.entity.Formation;
import com.unchk.backend.schedules.enums.SessionType;
import com.unchk.backend.trainers.entity.Trainer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private SessionType sessionType;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private String room;

    @ManyToOne
    @JoinColumn(name = "formation_id")
    private Formation formation;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;
}

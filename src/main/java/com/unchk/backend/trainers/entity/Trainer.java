package com.unchk.backend.trainers.entity;

import com.unchk.backend.formations.entity.Formation;
import com.unchk.backend.students.entity.Gender;
import com.unchk.backend.trainers.enums.TrainerType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trainers")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private String phone;

    private String speciality;

    private String grade;

    @Enumerated(EnumType.STRING)
    private TrainerType type;




}

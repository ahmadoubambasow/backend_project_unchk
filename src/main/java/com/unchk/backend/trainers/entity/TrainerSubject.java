package com.unchk.backend.trainers.entity;

import com.unchk.backend.subjects.entity.Subject;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trainer_subjects")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainerSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "trainer_id",
            nullable = false
    )
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(
            name = "subject_id",
            nullable = false
    )
    private Subject  subject;
}

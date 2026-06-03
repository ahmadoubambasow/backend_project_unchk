package com.unchk.backend.formations.entity;

import com.unchk.backend.users.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "formation_trainers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormationTrainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Formation concernée
     */
    @ManyToOne
    @JoinColumn(name = "formation_id")
    private Formation formation;

    /**
     * Formateur
     */
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private User trainer;
}
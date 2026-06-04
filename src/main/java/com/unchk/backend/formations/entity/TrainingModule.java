package com.unchk.backend.formations.entity;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "training_modules")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingModule {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private String title;

    @Column(length = 3000)
    private String description;

    private Integer hours;

    @ManyToOne
    @JoinColumn(
            name = "formation_id"
    )
    private Formation formation;
}
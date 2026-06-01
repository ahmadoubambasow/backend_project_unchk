package com.unchk.backend.groups.entity;

import com.unchk.backend.formations.entity.Formation;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_groups")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentGroup {

    /**
     * ID
     */
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    /**
     * Nom groupe
     * Ex:
     * L1-A
     * L1-B
     */
    @Column(nullable = false)
    private String name;

    /**
     * Capacité groupe
     */
    private Integer capacity;

    /**
     * Formation associée
     */
    @ManyToOne
    @JoinColumn(
            name = "formation_id",
            nullable = false
    )
    private Formation formation;
}
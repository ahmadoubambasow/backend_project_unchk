package com.unchk.backend.insertion.entity;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "partners")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Partner {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    private String contactPerson;

    private String phone;

    private String email;

    private String address;

    private String sector;

    @Column(length = 3000)
    private String description;
}
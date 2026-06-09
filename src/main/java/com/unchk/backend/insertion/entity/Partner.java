package com.unchk.backend.insertion.entity;

import com.unchk.backend.common.entity.BaseEntity;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "partners")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Partner extends BaseEntity {

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
package com.unchk.backend.hr.entity;

import com.unchk.backend.users.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.sql.ConnectionBuilder;
import java.time.LocalDate;

@Entity
@Table(name = "personnel_files")

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonnelFile {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @OneToOne
    private User user;

    private String employeeNumber;

    private String contractType;

    private LocalDate hireDate;

    private String position;

    private Double salary;

    private String diploma;

    private String phone;

    private String address;

    private String filePath;


}
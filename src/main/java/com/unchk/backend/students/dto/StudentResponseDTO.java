package com.unchk.backend.students.dto;

import com.unchk.backend.students.entity.Gender;
import com.unchk.backend.students.entity.StudentStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO réponse étudiant
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponseDTO {

    private Long id;

    private String matricule;

    private String firstName;

    private  String lastName;

    private String email;

    private String phone;

    private Gender  gender;

    private LocalDate birthDate;

    private String address;

    private StudentStatus status;

    private LocalDateTime createdAt;
}

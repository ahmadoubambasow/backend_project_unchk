package com.unchk.backend.hr.service;

import com.unchk.backend.administration.service.DocumentStorageService;
import com.unchk.backend.hr.dto.PersonnelFileRequestDTO;
import com.unchk.backend.hr.dto.PersonnelFileResponseDTO;
import com.unchk.backend.hr.entity.PersonnelFile;
import com.unchk.backend.hr.repository.PersonnelFileRepository;
import com.unchk.backend.users.entity.User;
import com.unchk.backend.users.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonnelFileService {

    private final PersonnelFileRepository
            repository;

    private final UserRepository
            userRepository;

    private final DocumentStorageService
            documentStorageService;

    /**
     * Création
     */
    public PersonnelFileResponseDTO create(
            PersonnelFileRequestDTO request
    ) {

        if (request.getUserId() == null) {

            throw new RuntimeException(
                    "Utilisateur obligatoire"
            );
        }

        if (
                repository.findByUserId(
                        request.getUserId()
                ).isPresent()
        ) {

            throw new RuntimeException(
                    "Cet utilisateur possède déjà un dossier RH"
            );
        }

        User user =

                userRepository

                        .findById(
                                request.getUserId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Utilisateur introuvable"
                                )
                        );

        PersonnelFile personnelFile =

                PersonnelFile.builder()

                        .user(user)

                        .contractType(
                                request.getContractType()
                        )

                        .hireDate(
                                request.getHireDate()
                        )

                        .position(
                                request.getPosition()
                        )

                        .salary(
                                request.getSalary()
                        )

                        .diploma(
                                request.getDiploma()
                        )

                        .phone(
                                request.getPhone()
                        )

                        .address(
                                request.getAddress()
                        )

                        .filePath(
                                request.getFilePath()
                        )

                        .build();

        personnelFile =
                repository.save(
                        personnelFile
                );

        personnelFile.setEmployeeNumber(

                String.format(
                        "EMP-%d-%04d",
                        java.time.Year.now().getValue(),
                        personnelFile.getId()
                )
        );

        personnelFile =
                repository.save(
                        personnelFile
                );

        return mapToResponse(
                personnelFile
        );
    }

    /**
     * Modification
     */
    public PersonnelFileResponseDTO update(

            Long id,

            PersonnelFileRequestDTO request

    ) {

        PersonnelFile personnelFile =

                repository

                        .findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Dossier introuvable"
                                )
                        );

        User user =

                userRepository

                        .findById(
                                request.getUserId()
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Utilisateur introuvable"
                                )
                        );

        personnelFile.setUser(
                user
        );

        personnelFile.setContractType(
                request.getContractType()
        );

        personnelFile.setHireDate(
                request.getHireDate()
        );

        personnelFile.setPosition(
                request.getPosition()
        );

        personnelFile.setSalary(
                request.getSalary()
        );

        personnelFile.setDiploma(
                request.getDiploma()
        );

        personnelFile.setPhone(
                request.getPhone()
        );

        personnelFile.setAddress(
                request.getAddress()
        );

        personnelFile.setFilePath(
                request.getFilePath()
        );

        personnelFile = repository.save(
                personnelFile
        );

        return mapToResponse(
                personnelFile
        );
    }

    /**
     * Liste
     */
    public List<PersonnelFileResponseDTO>
    getAll() {

        return repository

                .findAll()

                .stream()

                .map(
                        this::mapToResponse
                )

                .toList();
    }

    /**
     * Détail
     */
    public PersonnelFileResponseDTO getById(

            Long id

    ) {

        PersonnelFile personnelFile =

                repository

                        .findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Dossier introuvable"
                                )
                        );

        return mapToResponse(
                personnelFile
        );
    }

    /**
     * Suppression
     */
    public void delete(

            Long id

    ) {

        repository.deleteById(
                id
        );
    }

    /**
     * Upload document
     */
    public String uploadDocument(

            MultipartFile file

    ) {

        try {

            return documentStorageService
                    .storeDocument(file);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erreur upload fichier : "
                            + e.getMessage()
            );
        }
    }

    private String generateEmployeeNumber() {

        long count =
                repository.count() + 1;

        return String.format(
                "EMP-%d-%04d",
                java.time.Year.now().getValue(),
                count
        );
    }

    /**
     * Mapping
     */
    private PersonnelFileResponseDTO
    mapToResponse(

            PersonnelFile personnelFile

    ) {

        return PersonnelFileResponseDTO

                .builder()

                .id(
                        personnelFile.getId()
                )

                .userId(
                        personnelFile.getUser().getId()
                )

                .fullName(
                        personnelFile.getUser().getFullName()
                )

                .role(
                        personnelFile.getUser()
                                .getRole()
                                .getName()
                )

                .employeeNumber(
                        personnelFile.getEmployeeNumber()
                )

                .contractType(
                        personnelFile.getContractType()
                )

                .hireDate(
                        personnelFile.getHireDate()
                )

                .position(
                        personnelFile.getPosition()
                )

                .salary(
                        personnelFile.getSalary()
                )

                .diploma(
                        personnelFile.getDiploma()
                )

                .phone(
                        personnelFile.getPhone()
                )

                .address(
                        personnelFile.getAddress()
                )

                .filePath(
                        personnelFile.getFilePath()
                )

                .build();
    }
}
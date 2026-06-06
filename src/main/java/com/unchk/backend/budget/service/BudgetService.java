package com.unchk.backend.budget.service;

import com.unchk.backend.administration.service.DocumentStorageService;
import com.unchk.backend.budget.dto.BudgetRequestDTO;
import com.unchk.backend.budget.dto.BudgetResponseDTO;
import com.unchk.backend.budget.entity.Budget;
import com.unchk.backend.budget.repository.BudgetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepository
            repository;

    private final DocumentStorageService documentStorageService;

    public BudgetResponseDTO create(
            BudgetRequestDTO request
    ) {

        Budget budget =

                Budget.builder()

                        .year(
                                request.getYear()
                        )

                        .title(
                                request.getTitle()
                        )

                        .type(
                                request.getType()
                        )

                        .plannedAmount(
                                request.getPlannedAmount()
                        )

                        .executedAmount(
                                request.getExecutedAmount()
                        )

                        .description(
                                request.getDescription()
                        )

                        .documentPath(
                                request.getDocumentPath()
                        )

                        .build();

        return mapToResponse(
                repository.save(
                        budget
                )
        );
    }

    public List<BudgetResponseDTO>
    getAll() {

        return repository

                .findAll()

                .stream()

                .map(
                        this::mapToResponse
                )

                .toList();
    }

    public BudgetResponseDTO
    getById(Long id) {

        return repository

                .findById(id)

                .map(
                        this::mapToResponse
                )

                .orElseThrow();
    }

    public BudgetResponseDTO update(

            Long id,

            BudgetRequestDTO request

    ) {

        Budget budget =

                repository

                        .findById(id)

                        .orElseThrow();

        budget.setYear(
                request.getYear()
        );

        budget.setTitle(
                request.getTitle()
        );

        budget.setType(
                request.getType()
        );

        budget.setPlannedAmount(
                request.getPlannedAmount()
        );

        budget.setExecutedAmount(
                request.getExecutedAmount()
        );

        budget.setDescription(
                request.getDescription()
        );

        budget.setDocumentPath(
                request.getDocumentPath()
        );

        return mapToResponse(
                repository.save(
                        budget
                )
        );
    }

    public void delete(
            Long id
    ) {

        repository.deleteById(id);
    }

    public String uploadDocument(
            MultipartFile file
    ) {

        try {

            return documentStorageService
                    .storeDocument(file);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erreur lors de l'upload du document : "
                            + e.getMessage()
            );
        }
    }

    private BudgetResponseDTO
    mapToResponse(
            Budget budget
    ) {

        double planned =

                budget.getPlannedAmount() != null
                        ? budget.getPlannedAmount()
                        : 0;

        double executed =

                budget.getExecutedAmount() != null
                        ? budget.getExecutedAmount()
                        : 0;

        return BudgetResponseDTO

                .builder()

                .id(
                        budget.getId()
                )

                .year(
                        budget.getYear()
                )

                .title(
                        budget.getTitle()
                )

                .type(
                        budget.getType()
                )

                .plannedAmount(
                        planned
                )

                .executedAmount(
                        executed
                )

                .variance(
                        executed - planned
                )

                .description(
                        budget.getDescription()
                )

                .documentPath(
                        budget.getDocumentPath()
                )

                .build();
    }
}

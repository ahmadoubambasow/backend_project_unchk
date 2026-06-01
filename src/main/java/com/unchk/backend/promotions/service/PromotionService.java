package com.unchk.backend.promotions.service;

import com.unchk.backend.promotions.dto.PromotionRequestDTO;
import com.unchk.backend.promotions.dto.PromotionResponseDTO;
import com.unchk.backend.promotions.entity.Promotion;
import com.unchk.backend.promotions.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;

    /**
     * Création promotion
     */
    /**
     * Création promotion
     */
    public PromotionResponseDTO createPromotion(
            PromotionRequestDTO request
    ) {

        promotionRepository.findByName(
                        request.getName()
                )
                .ifPresent(promotion -> {

                    throw new RuntimeException(
                            "Cette promotion existe déjà"
                    );
                });

        Promotion promotion = Promotion.builder()

                .name(
                        request.getName()
                )

                .academicYear(
                        request.getAcademicYear()
                )

                .capacity(
                        request.getCapacity()
                )

                .build();

        Promotion savedPromotion =

                promotionRepository.save(
                        promotion
                );

        return mapToResponse(
                savedPromotion
        );
    }

    /**
     * Liste promotions
     */
    public List<PromotionResponseDTO> getAllPromotions() {

        return promotionRepository.findAll()

                .stream()

                .map(this::mapToResponse)

                .toList();
    }

    /**
     * Mise à jour promotion
     */
    public PromotionResponseDTO updatePromotion(

            Long id,

            PromotionRequestDTO request
    ) {

        Promotion promotion =

                promotionRepository.findById(
                                id
                        )

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Promotion introuvable"
                                )
                        );

        promotion.setName(
                request.getName()
        );

        promotion.setAcademicYear(
                request.getAcademicYear()
        );

        promotion.setCapacity(
                request.getCapacity()
        );

        Promotion updatedPromotion =

                promotionRepository.save(
                        promotion
                );

        return mapToResponse(
                updatedPromotion
        );
    }

    /**
     * Suppression promotion
     */
    public void deletePromotion(
            Long id
    ) {

        Promotion promotion =

                promotionRepository.findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Promotion introuvable"
                                )
                        );

        promotionRepository.delete(
                promotion
        );
    }

    /**
     * Mapping Entity -> DTO
     */
    private PromotionResponseDTO mapToResponse(
            Promotion promotion
    ) {

        return PromotionResponseDTO

                .builder()

                .id(
                        promotion.getId()
                )

                .name(
                        promotion.getName()
                )

                .academicYear(
                        promotion.getAcademicYear()
                )

                .capacity(
                        promotion.getCapacity()
                )

                .build();
    }
}

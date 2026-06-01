package com.unchk.backend.promotions.controller;

import com.unchk.backend.promotions.dto.PromotionRequestDTO;
import com.unchk.backend.promotions.dto.PromotionResponseDTO;
import com.unchk.backend.promotions.service.PromotionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promotions")
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionService  promotionService;

    /**
     * Création promotion
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN', 'SUPER_ADMIN')"
    )
    @PostMapping
    public PromotionResponseDTO createPromotion(

            @Valid
            @RequestBody
            PromotionRequestDTO request
    ) {

        return promotionService.createPromotion(
                request
        );
    }

    /**
     * Liste promotions.
     */
    @GetMapping
    public List<PromotionResponseDTO>
    getAllPromotions() {

        return promotionService.getAllPromotions();
    }

    /**
     * Mise à jour promotion.
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN', 'SUPER_ADMIN')"
    )
    @PutMapping("/{id}")
    public PromotionResponseDTO updatePromotion(

            @PathVariable
            Long id,

            @Valid
            @RequestBody
            PromotionRequestDTO request
    ) {

        return promotionService.updatePromotion(
                id,
                request
        );
    }

    /**
     * Suppression promotion.
     */
    @PreAuthorize(
            "hasAnyRole('ADMIN', 'SUPER_ADMIN')"
    )
    @DeleteMapping("/{id}")
    public void deletePromotion(

            @PathVariable
            Long id
    ) {

        promotionService.deletePromotion(
                id
        );
    }
}

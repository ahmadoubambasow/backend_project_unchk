package com.unchk.backend.promotions.controller;

import com.unchk.backend.promotions.dto.PromotionRequestDTO;
import com.unchk.backend.promotions.dto.PromotionResponseDTO;
import com.unchk.backend.promotions.service.PromotionService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST de gestion des promotions.
 */
@RestController
@RequestMapping("/api/promotions")
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionService promotionService;

    /**
     * Création d'une promotion.
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     * - RESPONSABLE_FORMATION
     */
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'DIRECTION'," +
                    "'RESPONSABLE_FORMATION'" +
                    ")"
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
     * Liste des promotions.
     *
     * Autorisés :
     * - Tous les utilisateurs authentifiés
     *
     * Remarque :
     * Les promotions sont des données académiques
     * consultables par les étudiants, enseignants
     * et responsables.
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<PromotionResponseDTO>
    getAllPromotions() {

        return promotionService.getAllPromotions();
    }

    /**
     * Mise à jour d'une promotion.
     *
     * Autorisés :
     * - ADMIN
     * - DIRECTION
     * - RESPONSABLE_FORMATION
     */
    @PreAuthorize(
            "hasAnyRole(" +
                    "'ADMIN'," +
                    "'DIRECTION'," +
                    "'RESPONSABLE_FORMATION'" +
                    ")"
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
     * Suppression d'une promotion.
     *
     * Autorisés :
     * - ADMIN uniquement
     *
     * Remarque :
     * La suppression d'une promotion peut avoir
     * des impacts importants sur les étudiants
     * et les groupes associés.
     */
    @PreAuthorize(
            "hasRole('ADMIN')"
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
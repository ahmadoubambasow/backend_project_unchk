package com.unchk.backend.common.controller;

/**
 * Controller de test.
 * Permet de vérifier que l'API fonctionne correctement
 */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    /**
     * Endpoint de vérification.
     *
     * @return message de succès
     */
    @GetMapping("/")
    public String healtCheck() {
        return "UNCHK Backend API is running successfully!";
    }
}
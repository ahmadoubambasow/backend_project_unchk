package com.unchk.backend.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

/**
 * Service de gestion JWT.
 */
@Service
public class JwtService {

    /**
     * Clé secrète JWT.
     */
    private static final String SECRET_KEY =
            "uchk_super_secret_key_2026_very_secure_key";

    /**
     * Génère une clé sécurisée.
     */
    private Key getSigningKey() {

        return Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes(StandardCharsets.UTF_8)
        );
    }

    /**
     * Génère un token JWT.
     *
     * @param email email utilisateur
     * @return token JWT
     */
    public String generateToken(String email) {

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 86400000)
                )
                .signWith(
                        getSigningKey(),
                        SignatureAlgorithm.HS256
                )
                .compact();
    }

    /**
     * Extrait email depuis token
     *
     * @param token JWT token
     * @return email utilisateur
     */
    public String extractEmail(String token) {

        return extractClaims(token).getSubject();
    }

    /**
     * Vérifie la validité du token
     *
     * @param token JWT token
     * @return true si valide
     */
    public boolean isTokenValid(String token) {

        return !extractClaims(token)
                .getExpiration()
                .before(new Date());
    }

    /**
     * Extraction claims JWT
     *
     * @param token JWT token
     * @return claims JWT
     */
    private Claims extractClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
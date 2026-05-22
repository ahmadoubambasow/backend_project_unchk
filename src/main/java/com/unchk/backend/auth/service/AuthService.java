package com.unchk.backend.auth.service;

import com.unchk.backend.auth.dto.LoginRequestDTO;
import com.unchk.backend.auth.dto.LoginResponseDTO;
import com.unchk.backend.auth.service.AuthService;
import com.unchk.backend.users.entity.User;
import com.unchk.backend.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service d'authentification
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final com.unchk.backend.auth.jwt.JwtService jwtService;

    /**
     * Authentifie un utilisateur
     *
     * @param request données login
     * @return response JWT
     */
    public LoginResponseDTO login(LoginRequestDTO request) {

        // Recherche utilisateur
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Utilisateur introuvable")
                        );

        // Vérification mot de passe
        boolean passwordMatches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!passwordMatches) {
            throw new RuntimeException("Utilisateur introuvable");
        }

        // Génération token JWT
        String token = jwtService.generateToken(user.getEmail());

        // return
        return LoginResponseDTO.builder()
                .token(token)
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole().getName())
                .build();
    }



}

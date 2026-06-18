package com.unchk.backend.security.filter;

import com.unchk.backend.auth.jwt.JwtService;
import com.unchk.backend.security.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * FILTRE JWT
 *
 * Vérifie chaque requete HTTP
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // Header Authorization
        final String authHeader = request.getHeader("Authorization");

        // Vérification présence token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        // Extraction token
        String jwtToken = authHeader.substring(7);

        // Extraction email
        String userEmail = jwtService.extractEmail(jwtToken);

        // Vérification utilisateur non authentifié
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Validation token
            if (jwtService.isTokenValid(jwtToken)) {

                // Charger USER
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(userEmail);

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                        );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                // Authentification Spring Security
                SecurityContextHolder.getContext()
                        .setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}

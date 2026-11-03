package com.example.Backend.config;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

/**
 * Protects endpoints that expose or remove other people's data (list / delete wishes).
 * Send header X-Admin-Key. If ADMIN_API_KEY is not set, those endpoints stay disabled.
 */
@Component
public class AdminGuard {

    private final String adminKey;

    public AdminGuard(@Value("${app.admin-key:}") String adminKey) {
        this.adminKey = adminKey;
    }

    public void require(String provided) {
        if (adminKey.isBlank() || provided == null
                || !MessageDigest.isEqual(adminKey.getBytes(StandardCharsets.UTF_8),
                                          provided.getBytes(StandardCharsets.UTF_8))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admin key required");
        }
    }
}

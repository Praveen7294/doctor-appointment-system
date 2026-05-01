package com.praveen.doctor_appointment_system.security;

import com.praveen.doctor_appointment_system.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "APFkjMMU0XLosgbY0MT5DKYE4Ke5BSci6jldd1oREp4";

    private static final long EXPIRATION_TIME = 1000* 60 * 60 * 24; // one day

    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(User user) {
        Date now = new Date();
        Date expiryTime = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("role", user.getRole().name())
                .issuedAt(now)
                .expiration(expiryTime)
                .signWith(getSignInKey())
                .compact();
    }

    public UUID extractUserId(String token) {
        String userId = extractClaims(token).getSubject();
        return UUID.fromString(userId);
    }

    public String extractEmail(String token) {
        return extractClaims(token).get("email", String.class);
    }

    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    public boolean isValidToken(String token) {
        return !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token)
                .getExpiration()
                .before(new Date());
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}

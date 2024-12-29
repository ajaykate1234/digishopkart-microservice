package com.digishopkart.product_service.util;

import io.jsonwebtoken.*;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import static io.jsonwebtoken.Jwts.*;

// Created a utility class to generate and validate JWT tokens.

@Configuration
public class JWTUtil {
    private String secretKey = "********";

    // Generate JWT Token
    public String generateToken(String username) {
        return builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Extract Username from Token
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Extract Claims
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    // Validate Token
    public boolean validateToken(String token, String username){
        return (username.equals(extractUsername(token)) && isTokenExpired(token));
    }

    // Check if token is Expired
    private boolean isTokenExpired(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }


}

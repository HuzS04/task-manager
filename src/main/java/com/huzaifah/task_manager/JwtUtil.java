package com.huzaifah.task_manager;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

// helper class that handles everything JWT related
// generate tokens, extract username from tokens, validate tokens
@Component // tells Spring to manage this class like a Service
public class JwtUtil {

    // reads jwt.secret from application.properties
    // this is the secret key used to sign and verify tokens
    // anyone with this key can generate valid tokens so keep it secret
    @Value("${jwt.secret}")
    private String secret;

    // reads jwt.expiration from application.properties
    // how long a token lasts in milliseconds (86400000 = 24 hours)
    @Value("${jwt.expiration}")
    private Long expiration;

    // converts the secret string into a proper cryptographic key
    // the JWT library needs it in this format to sign/verify tokens
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // generates a JWT token for a given username/email
    // called when a user logs in successfully
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // store who this token belongs to
                .setIssuedAt(new Date()) // store when it was created
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // store when it expires
                .signWith(getSigningKey()) // stamp it with the secret key
                .compact(); // build the final token string
    }

    // extracts the username from a token
    // called when a request comes in to find out who sent it
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // use secret key to verify the stamp
                .build()
                .parseClaimsJws(token) // decode and verify the token
                .getBody()
                .getSubject(); // get the username stored inside
    }

    // checks if a token is valid and not expired
    // returns true if valid, false if tampered with or expired
    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token); // if this succeeds the token is valid
            return true;
        } catch (JwtException e) {
            // JwtException is thrown if token is expired, tampered with, or invalid
            return false;
        }
    }
}

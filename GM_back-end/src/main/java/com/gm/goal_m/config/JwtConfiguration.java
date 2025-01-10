package com.gm.goal_m.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

@Configuration
public class JwtConfiguration {

    @Value("${jwt.secret}")
    private String secretKey;

    public Key getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}
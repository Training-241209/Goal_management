package com.gm.goal_m.service;

import org.springframework.stereotype.Service;
import com.gm.goal_m.config.JwtConfiguration;
import com.gm.goal_m.model.User;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    private final JwtConfiguration jwtConfiguration;

    @Autowired
    public JwtService(JwtConfiguration jwtConfiguration){
        this.jwtConfiguration = jwtConfiguration;
    }

    public String generateToken(User user) {
        return Jwts.builder()

                .claim("username", user.getUsername())
                .claim("firstName", user.getFirstName())
                .claim("lastName", user.getLastName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) 
                .signWith(jwtConfiguration.getSecretKey())
                .compact();
    }

  
    public User decodeToken(String token) {
        var claims = Jwts.parserBuilder()
                .setSigningKey(jwtConfiguration.getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return new User(claims.get("username", String.class), claims.get("firstName", String.class),
        claims.get("lastName", String.class));
    }
}
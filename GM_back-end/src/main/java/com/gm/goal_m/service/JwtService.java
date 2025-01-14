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
    public JwtService(JwtConfiguration jwtConfiguration) {
        this.jwtConfiguration = jwtConfiguration;
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .claim("email", user.getEmail())
                .claim("firstname", user.getFirstName())
                .claim("lastname", user.getLastName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(jwtConfiguration.getSecretKey())
                .compact();
    }

    public String decodeTokenEmail(String token) {
        var claims = Jwts.parserBuilder()
                .setSigningKey(jwtConfiguration.getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String email = claims.get("email", String.class);
        String firstname = claims.get("firstname", String.class);
        String lastname = claims.get("lastname", String.class);
        return email + ", " + firstname + ", " + lastname;
    }

    public String decodeToken(String token) {
        var claims = Jwts.parserBuilder()
                .setSigningKey(jwtConfiguration.getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("email", String.class);
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtConfiguration.getSecretKey())
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            System.out.println("Token has expired: " + e.getMessage());
            return false;
        } catch (io.jsonwebtoken.SignatureException e) {
            System.out.println("Invalid token signature: " + e.getMessage());
            return false;
        } catch (io.jsonwebtoken.JwtException e) {
            System.out.println("Invalid token: " + e.getMessage());
            return false;
        }
    }

}
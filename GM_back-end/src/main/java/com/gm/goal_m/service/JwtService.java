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
                .claim("userId", user.getUserId())
                .claim("email", user.getEmail())
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

        return new User(claims.get("email", String.class), claims.get("firstName", String.class),
                claims.get("lastName", String.class));
    }

    public User decodeTokenById(String token) {
        var claims = Jwts.parserBuilder()
                .setSigningKey(jwtConfiguration.getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return new User(claims.get("userId", Long.class));
    }

    /**
     * @param token
     * @return
     */
    public long  getUserId(String token) {
        var claims = Jwts.parserBuilder()
                .setSigningKey(jwtConfiguration.getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return new User(claims.get("userId", Long.class)).getUserId();
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
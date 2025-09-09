package org.carpio.com.demoapp6.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {
    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    public String generateToken(UserDetails userDetails) {
        // 60 s
        long expiration = 10000 * 60;
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey)
                .compact();
    }
    public SecretKey getSecretKey() {
        return secretKey;
    }

}

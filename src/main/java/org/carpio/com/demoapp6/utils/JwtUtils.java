package org.carpio.com.demoapp6.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    public String generateToken(UserDetails userDetails) {
        // 600 s
        long expiration = 1000 * 600;

        String scope = userDetails.getAuthorities().stream()
                .map(auth -> auth.getAuthority().replace("ROLE_", "").toLowerCase())
                .collect(Collectors.joining(" "));

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("scope", scope)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey)
                .compact();
    }
    public SecretKey getSecretKey() {
        return secretKey;
    }

}

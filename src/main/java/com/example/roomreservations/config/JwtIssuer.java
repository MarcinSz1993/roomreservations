package com.example.roomreservations.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
@Component
@RequiredArgsConstructor
public class JwtIssuer {
 private final JwtProperties jwtProperties;
    public String issue(Long userId, String email, List<String> roles){
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(Instant.now().plus(Duration.ofDays(1)))
                .withClaim("email",email)
                .withClaim("role",roles)
                .sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
    }
}

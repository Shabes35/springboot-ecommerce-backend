package com.ecommerce.security;


import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;


import java.util.Date;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;


@Service
public class JwtService {

    private static  final String SECRET_KEY =  "THIS_IS_MY_SUPER_SECRET_KEY_FOR_ECOMMERCE_PROJECT_2026_CHANGE_ME";

    private SecretKey getSignInKey(){
       return  Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }
    public String generateToken(String email){
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000  * 60 * 60 * 24))
                .signWith(getSignInKey())
                .compact();
    }

    public String extractEmail(String token){

        Claims claims = Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    public boolean isTokenValid(String token){
        try {
            extractEmail(token);
            return true;

        }catch (Exception e){
            return false;
        }
    }
}

package com.example.cling.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class JwtUtil {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); //비밀 키 생성
    private static final Set<String> blacklistedTokens = new HashSet<>(); // 블랙리스트를 위한 Set

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10시간
                .signWith(secretKey) // 비밀 키를 사용
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey) // 비밀 키 사용
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token, String username) {
        return !blacklistedTokens.contains(token) && username.equals(extractClaims(token).getSubject()) && !isTokenExpired(token);
    }

    public void blacklistToken(String token) {
        blacklistedTokens.add(token); // 블랙리스트에 추가
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}

package com.example.flightapi.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;

import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private static String secret;
    private static String expiration;
    private static String refreshExpiration;

    @Value("${jwt.secret}")
    public void setSecret(String value) {
        secret = value;
    }

    @Value("${jwt.expiration}")
    public void setExpiration(String value) {
        expiration = value;
    }

    @Value("${jwt.refreshExpiration}")
    public void setRefreshExpiration(String value) {
        refreshExpiration = value;
    }

    public static String generateToken(String username, String userId) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        // 设置过期时间为当前时间 + expiration 毫秒
        Date expirationDate = Date.from(Instant.now().plusMillis(Long.parseLong(expiration)));
        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .setExpiration(expirationDate) // new Date(System.currentTimeMillis() + expiration)
                .signWith(key)
                .compact();
    }

    public static String generateRefreshToken(String username, String userId) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        // 设置过期时间为当前时间 + refreshExpiration 毫秒
        Date refreshExpirationDate = Date.from(Instant.now().plusMillis(Long.parseLong(refreshExpiration)));
        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .setExpiration(refreshExpirationDate) // new Date(System.currentTimeMillis() + refreshExpiration)
                .signWith(key)
                .compact();
    }

    public static String getUsernameFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static String getUserIdFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("userId", String.class);
    }

    public static String validateToken(String token) throws Exception {

        try {
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            // Token 已过期处理
            throw new ExpiredJwtException(null, null, "Token expired");
        } catch (MalformedJwtException e) {
            // Token 格式错误
            throw new MalformedJwtException("Invalid token format");
        } catch (JwtException e) {
            // 其他JWT相关错误
            throw new JwtException("Invalid token");
        }
    }
}

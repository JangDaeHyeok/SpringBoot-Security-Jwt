package com.jdh.springSecurityJwt.config.security.provider;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtProvider {

    // jwt 만료 시간 1시간
    private static final long JWT_TOKEN_VALID = (long) 1000 * 60 * 30;

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * token Username 조회
     *
     * @param token JWT
     * @return token Username
     */
    public String getUsernameFromToken(final String token) {
        return getClaimFromToken(token, Claims::getId);
    }

    /**
     * token 사용자 속성 정보 조회
     *
     * @param token JWT
     * @param claimsResolver Get Function With Target Claim
     * @param <T> Target Claim
     * @return 사용자 속성 정보
     */
    public <T> T getClaimFromToken(final String token, final Function<Claims, T> claimsResolver) {
        // token 유효성 검증
        validateToken(token);

        final Claims claims = getAllClaimsFromToken(token);

        return claimsResolver.apply(claims);
    }

    /**
     * token 사용자 모든 속성 정보 조회
     *
     * @param token JWT
     * @return All Claims
     */
    private Claims getAllClaimsFromToken(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 토큰 만료 일자 조회
     *
     * @param token JWT
     * @return 만료 일자
     */
    public Date getExpirationDateFromToken(final String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * access token 생성
     *
     * @param id token 생성 id
     * @return access token
     */
    public String generateAccessToken(final String id) {
        return generateAccessToken(id, new HashMap<>());
    }

    /**
     * access token 생성
     *
     * @param id token 생성 id
     * @return access token
     */
    public String generateAccessToken(final long id) {
        return generateAccessToken(String.valueOf(id), new HashMap<>());
    }

    /**
     * access token 생성
     *
     * @param id token 생성 id
     * @param claims token 생성 claims
     * @return access token
     */
    public String generateAccessToken(final String id, final Map<String, Object> claims) {
        return doGenerateAccessToken(id, claims);
    }

    /**
     * JWT access token 생성
     *
     * @param id token 생성 id
     * @param claims token 생성 claims
     * @return access token
     */
    private String doGenerateAccessToken(final String id, final Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setId(id)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALID)) // 30분
                .signWith(key)
                .compact();
    }

    /**
     * refresh token 생성
     *
     * @param id token 생성 id
     * @return refresh token
     */
    public String generateRefreshToken(final String id) {
        return doGenerateRefreshToken(id);
    }

    /**
     * refresh token 생성
     *
     * @param id token 생성 id
     * @return refresh token
     */
    public String generateRefreshToken(final long id) {
        return doGenerateRefreshToken(String.valueOf(id));
    }

    /**
     * refresh token 생성
     * @param id token 생성 id
     * @return refresh token
     */
    private String doGenerateRefreshToken(final String id) {
        return Jwts.builder()
                .setId(id)
                .setExpiration(new Date(System.currentTimeMillis() + (JWT_TOKEN_VALID * 2) * 24)) // 24시간
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(key)
                .compact();
    }

    /**
     * token 검증
     *
     * @param token JWT
     * @return token 검증 결과
     */
    public Boolean validateToken(final String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException e) {
            log.warn("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.warn("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.warn("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.warn("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.warn("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

}

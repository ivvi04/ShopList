package ru.lakeevda.authservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.lakeevda.authservice.entity.UserEntity;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${token.signing.key}")
    private String jwtSigningKey;

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractPhone(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(UserEntity user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("phone", user.getPhone());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());
        return generateToken(claims, user);
    }

    public boolean isTokenValid(String token, UserEntity user) {
        String phone = extractPhone(token);
        return phone.equals(user.getPhone()) && !isTokenNotExpired(token);
    }

    public boolean isTokenValid(String token) {
        try {
            getClaimsJws(token);
            String username = extractUserName(token);
            return username != null && isTokenNotExpired(token);
        } catch (SignatureException e) {
            return false;
        }
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        Claims claims = getClaimsJws(token).getPayload();
        return claimsResolvers.apply(claims);
    }

    private String generateToken(Map<String, Object> extraClaims, UserEntity user) {
        return Jwts.builder().claims(extraClaims).subject(String.valueOf(user.getPhone()))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 100000 * 60 * 24))
                .signWith(getSigningKey()).compact();
    }

    private boolean isTokenNotExpired(String token) {
        return !extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    private Jws<Claims> getClaimsJws(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

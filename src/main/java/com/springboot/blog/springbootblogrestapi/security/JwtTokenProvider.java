package com.springboot.blog.springbootblogrestapi.security;

import java.util.Date;

import com.springboot.blog.springbootblogrestapi.exception.BlogApiException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) {

        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);

        String token = Jwts.builder()
                       .setSubject(username)
                       .setIssuedAt(new Date())
                       .setExpiration(expireDate)
                       .signWith(SignatureAlgorithm.HS512, jwtSecret)
                       .compact();
                    
        return token;
    }

    public String getUsernameFromJwt(String token) {

        Claims claims = Jwts.parser()
                        .setSigningKey(jwtSecret)
                        .parseClaimsJws(token)
                        .getBody();
        
        return claims.getSubject();

    }

    public boolean validateToken(String token) {

        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);

            return true;
        }catch(SignatureException ex) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");

        }catch(MalformedJwtException ex) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Invalid Jwt token");

        }catch(ExpiredJwtException ex) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Expired Jwt token");

        }catch(UnsupportedJwtException ex) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Unsopported Jwt token");

        }catch(IllegalArgumentException ex) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Jwt claims string is empty");
        }
    }
    
}

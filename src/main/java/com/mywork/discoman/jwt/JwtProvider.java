package com.mywork.discoman.jwt;

import com.mywork.discoman.dto.jwt.TokenDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.stream.Collectors;

public class JwtProvider {

    @Value("jwt.access_expiration_time")
    private int ACCESS_EXPIRATION_TIME;
    @Value("jwt.refresh_expiration_time")
    private long REFRESH_EXPIRATION_TIME;
    @Value("jwt.secret")
    private String SECRET;

    public String createAccessToken(Authentication authentication){
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Long now = (new Date()).getTime();

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .setExpiration(new Date(now+ ACCESS_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }
    public String createRefreshToken(){
        Long now = (new Date()).getTime();

        return Jwts.builder()
                .setExpiration(new Date(now+ REFRESH_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }
}

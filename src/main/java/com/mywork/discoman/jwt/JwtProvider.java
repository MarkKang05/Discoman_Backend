package com.mywork.discoman.jwt;

import com.mywork.discoman.dto.jwt.TokenDto;
import com.mywork.discoman.service.interfaces.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("jwt.access_expiration_time")
    private static int ACCESS_EXPIRATION_TIME;
    @Value("jwt.refresh_expiration_time")
    private static long REFRESH_EXPIRATION_TIME;
//    @Value("$(jwt.secret}")
    private String SECRET = "EltonJohnGoodbyeYellowBrickRoadEltonJohnDiamonds";
//    private final Key key;


//    public JwtProvider(@Value("${jwt.secret}") String secretKey) {
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        this.key = Keys.hmacShaKeyFor(keyBytes);
//    }

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

//    public Authentication getAuthentication(String accessToken){
//        Claims claims = parseClaims(accessToken);
//
//        if (claims.get("auth") == null){
//            throw new RuntimeException("Null Authority");
//        }
//
//        Collection<? extends GrantedAuthority> authorities = userService.getUserAuthority(claims.getSubject());
//    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(SECRET).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }
}

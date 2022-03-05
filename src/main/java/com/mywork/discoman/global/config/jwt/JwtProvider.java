package com.mywork.discoman.global.config.jwt;

import com.mywork.discoman.domain.authority.domain.Authority;
import com.mywork.discoman.domain.user.domain.User;
import com.mywork.discoman.domain.authority.dao.AuthorityRepository;
import com.mywork.discoman.domain.user.dao.UserRepository;
import com.mywork.discoman.global.common.service.RedisService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtProvider {

//    @Value("jwt.access_expiration_time")
    private static int ACCESS_EXPIRATION_TIME = 60000;
//    @Value("jwt.refresh_expiration_time")
    private static long REFRESH_EXPIRATION_TIME = 120000;
//    @Value("$(jwt.secret}")
    private String SECRET = "EltonJohnGoodbyeYellowBrickRoadEltonJohnDiamonds";
//    private final Key key;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final RedisService redisService;


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
    public Authentication getAuthentication(Long id){

        User user = userRepository.findById(id).get();
        List<Authority> authorities = authorityRepository.findByUserId(id);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        authorities.forEach(a -> grantedAuthorities.add(new SimpleGrantedAuthority(a.getRole().toString())));

        UserDetails principal = new org.springframework.security.core.userdetails.User(user.getUsername(), "", grantedAuthorities);

        return new UsernamePasswordAuthenticationToken(principal, "", grantedAuthorities);

    }
    public Authentication getAuthentication(String accessToken){
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null)
            throw new RuntimeException("Null Authority");

        User user = userRepository.findByUsername((String) claims.getSubject()).get();
        log.debug("F username: "+ claims.getSubject());
        log.debug("F username: "+ user.getUsername());
        List<Authority> authorities = authorityRepository.findByUserId(user.getId());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        authorities.forEach(a -> grantedAuthorities.add(new SimpleGrantedAuthority(a.getRole().toString())));

        UserDetails principal = new org.springframework.security.core.userdetails.User(claims.getSubject(), "", grantedAuthorities);

        return new UsernamePasswordAuthenticationToken(principal, "", grantedAuthorities);
    }
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.debug("Invalid JWT signature: " +  e.getMessage());
        } catch (MalformedJwtException e) {
            log.debug("Invalid JWT token: " +  e.getMessage());
        } catch (ExpiredJwtException e) {
            log.debug("JWT token is expired: " +  e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.debug("JWT token is unsupported: " +  e.getMessage());
        } catch (IllegalArgumentException e) {
            log.debug("JWT claims string is empty: " + e.getMessage());
        }
        return false;
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(SECRET).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }

    public JwtToken parseToken(HttpServletRequest request) {
        Cookie accessTokenCookie = WebUtils.getCookie(request, "accessToken");
        Cookie refreshTokenCookie = WebUtils.getCookie(request, "refreshToken");
        String accessToken=null;
        String refreshToken=null;

        try {
            accessToken = accessTokenCookie.getValue();
        } catch (Exception e){}

        try {
            refreshToken= refreshTokenCookie.getValue();
        } catch (NullPointerException e){}

        if (StringUtils.hasText(accessToken) || StringUtils.hasText(refreshToken))
            return JwtToken.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        return null;
    }

}

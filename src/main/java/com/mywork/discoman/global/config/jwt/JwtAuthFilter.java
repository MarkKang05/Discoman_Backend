package com.mywork.discoman.global.config.jwt;

import com.mywork.discoman.domain.user.dao.UserRepository;
import com.mywork.discoman.global.common.service.RedisService;
import com.mywork.discoman.domain.user.service.UserService;
import com.mywork.discoman.global.config.cookie.CustomCookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        JwtToken jwtToken = jwtProvider.parseToken(request);
        String accessToken;
        String refreshToken;
        try {
            accessToken = jwtToken.getAccessToken();
        } catch (Exception e) {
            accessToken = null;
        }
        try {
            refreshToken = jwtToken.getRefreshToken();
        } catch (Exception e) {
            refreshToken = null;
        }

        log.debug("AccessToken: "+ accessToken);
        log.debug("RefreshToken: "+ refreshToken);

        if(accessToken != null && jwtProvider.validateJwtToken(accessToken)){ // AT RT
            Authentication authentication = jwtProvider.getAuthentication(accessToken);
            log.debug("Valid Authentication");

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else if(accessToken == null && jwtProvider.validateJwtToken(refreshToken)){ // RT
            log.debug("Only AccessToken expired");

            Long userId = userRepository.findByUsername(redisService.getValues(refreshToken) ).get().getId();
            if (userId == null)
                log.error("NONEUSERID");

            Authentication authentication = jwtProvider.getAuthentication(userId);
            String issuedAccessToken = jwtProvider.createAccessToken(authentication);
            response.addCookie(new CustomCookie("accessToken", issuedAccessToken));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else if (refreshToken==null){ // RT 존재 x
            log.debug("RefreshToken expired");

            response.addCookie(new CustomCookie("accessToken", null, 0));
            response.addCookie(new CustomCookie("refreshToken", null, 0));
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);

    }

}

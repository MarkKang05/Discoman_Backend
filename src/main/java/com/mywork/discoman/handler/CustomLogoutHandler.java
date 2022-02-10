package com.mywork.discoman.handler;

import com.mywork.discoman.domain.User;
import com.mywork.discoman.jwt.JwtProvider;
import com.mywork.discoman.repository.UserRepository;
import com.mywork.discoman.service.RedisService;
import com.mywork.discoman.utils.CustomCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomLogoutHandler implements LogoutHandler {

    @Autowired
    private RedisService redisService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Cookie refreshTokenCookie = WebUtils.getCookie(request, "refreshToken");
        String refreshToken = refreshTokenCookie.getValue();

        redisService.delValues(refreshToken);
        response.addCookie(new CustomCookie("accessToken", null, 0));
        response.addCookie(new CustomCookie("refreshToken", null, 0));
        SecurityContextHolder.clearContext();
    }
}

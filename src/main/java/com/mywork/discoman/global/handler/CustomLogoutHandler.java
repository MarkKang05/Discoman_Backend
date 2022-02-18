package com.mywork.discoman.global.handler;

import com.mywork.discoman.global.common.service.RedisService;
import com.mywork.discoman.global.config.cookie.CustomCookie;
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

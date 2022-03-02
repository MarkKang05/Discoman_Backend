package com.mywork.discoman.domain.user.service;

import com.mywork.discoman.domain.user.dto.RequestLoginUserDto;
import com.mywork.discoman.domain.user.dto.RequestSaveUserDto;
import com.mywork.discoman.global.config.jwt.JwtToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface UserService{
    void createUser(RequestSaveUserDto requestSaveUserDto);
    JwtToken loginUser(RequestLoginUserDto requestLoginUserDto);
    Collection<? extends GrantedAuthority> getUserAuthority(String username);
}

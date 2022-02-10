package com.mywork.discoman.service.interfaces;

import com.mywork.discoman.domain.Authority;
import com.mywork.discoman.dto.auth.RequestLoginUserDto;
import com.mywork.discoman.dto.auth.RequestSaveUserDto;
import com.mywork.discoman.dto.jwt.TokenDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface UserService{
    // TODO: 2022/02/07 Edit User, Insert Image
    void createUser(RequestSaveUserDto requestSaveUserDto);
    TokenDto loginUser(RequestLoginUserDto requestLoginUserDto);
    Collection<? extends GrantedAuthority> getUserAuthority(String username);
}

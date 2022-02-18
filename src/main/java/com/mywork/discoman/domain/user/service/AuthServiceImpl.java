package com.mywork.discoman.domain.user.service;

import com.mywork.discoman.domain.user.service.AuthService;

public class AuthServiceImpl implements AuthService {

    @Override
    public boolean existEmail(String email) {
        return false;
    }

    @Override
    public boolean login(String email, String password) {
        return false;
    }
}

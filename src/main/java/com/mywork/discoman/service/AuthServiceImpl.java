package com.mywork.discoman.service;

import com.mywork.discoman.service.interfaces.AuthService;

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

package com.mywork.discoman.domain.user.service;

public interface AuthService {
    boolean existEmail(String email);
    boolean login(String email, String password);
}

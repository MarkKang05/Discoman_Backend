package com.mywork.discoman.service.interfaces;

public interface AuthService {
    boolean existEmail(String email);
    boolean login(String email, String password);
}

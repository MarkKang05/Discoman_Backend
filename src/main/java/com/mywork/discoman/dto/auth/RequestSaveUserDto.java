package com.mywork.discoman.dto.auth;

import com.mywork.discoman.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestSaveUserDto {
    private String username;
    private String email;
    private String password;

    public User toEntity(){
        return User.builder()
                .email(email)
                .username(username)
                .password(password)
                .build();
    }
}

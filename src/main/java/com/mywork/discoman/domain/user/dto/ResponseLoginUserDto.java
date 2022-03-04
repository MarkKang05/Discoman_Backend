package com.mywork.discoman.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseLoginUserDto {
    private String username;
    private String email;
}

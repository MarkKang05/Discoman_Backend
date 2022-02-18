package com.mywork.discoman.domain.user.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class RequestLoginUserDto {
    private String email;
    private String password;

}

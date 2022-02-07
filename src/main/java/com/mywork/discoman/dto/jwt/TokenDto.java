package com.mywork.discoman.dto.jwt;

import lombok.Builder;

@Builder
public class TokenDto {
    private String accessToken;
    private String refreshToken;
}

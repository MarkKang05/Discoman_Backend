package com.mywork.discoman.controller;

import com.mywork.discoman.dto.auth.RequestLoginUserDto;
import com.mywork.discoman.dto.auth.RequestSaveUserDto;
import com.mywork.discoman.dto.jwt.TokenDto;
import com.mywork.discoman.service.RedisService;
import com.mywork.discoman.service.interfaces.UserService;
import com.mywork.discoman.utils.CustomCookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;
    private final RedisService redisService;

    @PostMapping("/auth/signup")
    public ResponseEntity<?> createUser(@RequestBody RequestSaveUserDto requestSaveUserDto){
        log.debug(requestSaveUserDto.getEmail());
        userService.createUser(requestSaveUserDto);
        return ResponseEntity.ok(requestSaveUserDto);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> LoginUser(@RequestBody RequestLoginUserDto requestLoginUserDto, HttpServletResponse response){
        TokenDto tokenDto = userService.loginUser(requestLoginUserDto);

        if (tokenDto.getAccessToken() != null){
            response.addCookie(new CustomCookie("accessToken", tokenDto.getAccessToken(), 60000));
            response.addCookie(new CustomCookie("refreshToken", tokenDto.getRefreshToken(), 120000));
            return ResponseEntity.ok(tokenDto);
        } else{
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/auth/redis")
    public String getRedis(@RequestBody String key){
        return redisService.getValues(key);
    }
}

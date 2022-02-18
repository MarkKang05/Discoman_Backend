package com.mywork.discoman.domain.user.api;

import com.mywork.discoman.domain.user.dto.RequestLoginUserDto;
import com.mywork.discoman.domain.user.dto.RequestSaveUserDto;
import com.mywork.discoman.global.config.jwt.JwtToken;
import com.mywork.discoman.global.common.service.RedisService;
import com.mywork.discoman.domain.user.service.UserService;
import com.mywork.discoman.global.config.cookie.CustomCookie;
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
        JwtToken jwtToken = userService.loginUser(requestLoginUserDto);

        if (jwtToken.getAccessToken() != null){
            response.addCookie(new CustomCookie("accessToken", jwtToken.getAccessToken(), 60000));
            response.addCookie(new CustomCookie("refreshToken", jwtToken.getRefreshToken(), 120000));
            return ResponseEntity.ok(jwtToken);
        } else{
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/auth/redis")
    public String getRedis(@RequestBody String key){
        return redisService.getValues(key);
    }
}
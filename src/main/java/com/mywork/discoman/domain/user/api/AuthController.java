package com.mywork.discoman.domain.user.api;

import com.mywork.discoman.domain.user.domain.User;
import com.mywork.discoman.domain.user.dto.RequestLoginUserDto;
import com.mywork.discoman.domain.user.dto.RequestSaveUserDto;
import com.mywork.discoman.domain.user.service.UserServiceImpl;
import com.mywork.discoman.global.common.response.BasicResponse;
import com.mywork.discoman.global.common.response.CommonResponse;
import com.mywork.discoman.global.config.jwt.JwtToken;
import com.mywork.discoman.global.common.service.RedisService;
import com.mywork.discoman.domain.user.service.UserService;
import com.mywork.discoman.global.config.cookie.CustomCookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserServiceImpl userService;
    private final RedisService redisService;

    // TODO: 2022/03/02 exist username, email, update password,

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

    @GetMapping("/auth/admin/users")
    public ResponseEntity<? extends BasicResponse> getAllUser(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(new CommonResponse<List<User>>(users));
    }
}

package com.mywork.discoman.controller;

import com.mywork.discoman.dto.auth.RequestSaveUserDto;
import com.mywork.discoman.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;

    @PostMapping("/auth/signup")
    public ResponseEntity<?> createUser(@RequestBody RequestSaveUserDto requestSaveUserDto){
        log.debug(requestSaveUserDto.getEmail());
        userService.createUser(requestSaveUserDto);
        return ResponseEntity.ok(requestSaveUserDto);
    }
}

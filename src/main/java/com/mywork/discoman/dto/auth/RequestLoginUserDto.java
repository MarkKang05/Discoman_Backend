package com.mywork.discoman.dto.auth;

import com.mywork.discoman.repository.UserRepository;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Data
@Slf4j
public class RequestLoginUserDto {
    private String email;
    private String password;

}

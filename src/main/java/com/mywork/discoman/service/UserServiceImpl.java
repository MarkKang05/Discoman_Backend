package com.mywork.discoman.service;

import com.mywork.discoman.domain.Authority;
import com.mywork.discoman.domain.EnumRole;
import com.mywork.discoman.domain.User;
import com.mywork.discoman.dto.auth.RequestSaveUserDto;
import com.mywork.discoman.repository.AuthorityRepository;
import com.mywork.discoman.repository.UserRepository;
import com.mywork.discoman.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(RequestSaveUserDto request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        request.setPassword(encodedPassword);
        User user = userRepository.save(request.toEntity());
        Authority authority = Authority.builder()
                        .user(user).role(EnumRole.ROLE_USER).build();
//        Authority authority2 = Authority.builder()
//                .user(user).role(EnumRole.ROLE_ADMIN).build();
        authorityRepository.save(authority);
//        authorityRepository.save(authority2);
    }

}

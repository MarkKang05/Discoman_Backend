package com.mywork.discoman.service;

import com.mywork.discoman.domain.Authority;
import com.mywork.discoman.domain.EnumRole;
import com.mywork.discoman.domain.User;
import com.mywork.discoman.dto.auth.RequestLoginUserDto;
import com.mywork.discoman.dto.auth.RequestSaveUserDto;
import com.mywork.discoman.jwt.JwtProvider;
import com.mywork.discoman.repository.AuthorityRepository;
import com.mywork.discoman.repository.UserRepository;
import com.mywork.discoman.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisService redisService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Override
    public void createUser(RequestSaveUserDto request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        request.setPassword(encodedPassword);
        User user = userRepository.save(request.toEntity());
        Authority authority = Authority.builder()
                        .user(user).role(EnumRole.ROLE_USER).build();
        Authority authority2 = Authority.builder()
                .user(user).role(EnumRole.ROLE_ADMIN).build();
        authorityRepository.save(authority);
        authorityRepository.save(authority2);

//        Collection<? extends GrantedAuthority> userAuthority = getUserAuthority(user.getUsername());
//        userAuthority.forEach(a -> log.debug("user Auth: "+ a));
    }

    @Override
    public void loginUser(RequestLoginUserDto requestLoginUserDto) {
//        UsernamePasswordAuthenticationToken authenticationToken =  requestLoginUserDto.toAuthentication();
        String username = userRepository.findByEmail(requestLoginUserDto.getEmail()).getUsername();
        String password = requestLoginUserDto.getPassword();
        log.debug("request_username: "+ username);
        log.debug("request_password: "+ password);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
//        RequestLogin requestLogin;

        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String accessToken = jwtProvider.createAccessToken(authentication);
            String refreshToken = jwtProvider.createRefreshToken();
//            TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

//            RefreshToken refreshToken = new RefreshToken();
            log.debug("username::::: "+ username);
            log.debug("refreshToken::::: "+ refreshToken);
            redisService.setValues(refreshToken, username);
//            refreshToken.setUser(userRepository.findByEmail(requestLoginUserDto.getEmail()).get());
//            refreshToken.setExpiryDate(Instant.now().plusMillis(120000L));
//            refreshToken.setToken(tokenDto.getRefreshToken());

//            refreshToken = refreshTokenRepository.save(refreshToken);

//            requestLogin = new RequestLogin(tokenDto);

//            return requestLogin;
//        } catch (InternalAuthenticationServiceException e){
//            System.out.println("email error");
//            requestLogin = new RequestLogin("email");
//        } catch (BadCredentialsException e){
//            System.out.println("password error");
//            requestLogin = new RequestLogin("password");
        } catch (Exception e){
            log.debug(""+e);
        }
//        return requestLogin;

    }

    @Override
    public Collection<? extends GrantedAuthority> getUserAuthority(String username) {
        log.debug(username);
        User user = userRepository.findByUsername(username).get();
        List<Authority> authorities = authorityRepository.findByUserId(user.getId());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        authorities.forEach(a -> grantedAuthorities.add(new SimpleGrantedAuthority(a.getRole().toString())));
//        authorities.add(new SimpleGrantedAuthority(person.getAuth()));
//        for (Authority authority: authorities){
//            log.debug("Auth: "+authority.get);
//        }

        return grantedAuthorities;
//        Collection<? extends GrantedAuthority> collection = new ArrayList<GrantedAuthority>(authorities);
//        Collection<? extends GrantedAuthority> collection2 = (Collection<? extends GrantedAuthority>) authorities;
//        authorities.forEach(authority -> collection.add(authority));
//        return collection;
    }


}

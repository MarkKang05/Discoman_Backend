package com.mywork.discoman.domain.user.service;

import com.mywork.discoman.domain.authority.domain.Authority;
import com.mywork.discoman.domain.user.domain.User;
import com.mywork.discoman.domain.authority.dao.AuthorityRepository;
import com.mywork.discoman.domain.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
//    private final UserService userService;
    private final AuthorityRepository authorityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByUsername(username)
//                .map(this::createUserDetails)
//                .orElseThrow(() -> new InternalAuthenticationServiceException("사용자를 찾을 수 없음"));

        User user = userRepository.findByUsername(username).get();
        UserDetails userDetails = createUserDetails(user);
        log.debug("DB_username: "+ userDetails.getUsername());
        log.debug("DB_password: "+ userDetails.getPassword());
        userDetails.getAuthorities().forEach(a -> {
            log.debug("DB_role: "+ a.toString());
        });
        return userDetails;
    }

    private UserDetails createUserDetails(User user){
//        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole().toString());
//        userService.getUserAuthority(user.getUsername());
//        User user = userRepository.findByUsername(username).get();
        List<Authority> authorities = authorityRepository.findByUserId(user.getId());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        authorities.forEach(a -> grantedAuthorities.add(new SimpleGrantedAuthority(a.getRole().toString())));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                grantedAuthorities
//                userService.getUserAuthority(user.getUsername())
        );

    }


}

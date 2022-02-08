package com.mywork.discoman.service.interfaces;

import com.mywork.discoman.dto.auth.RequestSaveUserDto;
import org.springframework.stereotype.Service;

public interface UserService{
    // TODO: 2022/02/07 Edit User, Insert Image
    void createUser(RequestSaveUserDto requestSaveUserDto);
}

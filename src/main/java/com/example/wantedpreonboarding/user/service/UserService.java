package com.example.wantedpreonboarding.user.service;

import com.example.wantedpreonboarding.user.dto.UserDto;
import com.example.wantedpreonboarding.user.entity.UserEntity;

public interface UserService {

    UserDto findByUserEmail(String email);
    UserEntity userSave(UserDto userDto);
}

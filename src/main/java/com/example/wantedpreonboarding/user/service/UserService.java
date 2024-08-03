package com.example.wantedpreonboarding.user.service;

import com.example.wantedpreonboarding.user.dto.UserDto;
import com.example.wantedpreonboarding.user.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity userSave(UserDto userDto);
    void softDeleteByUserId(long userId);
    UserDto findByUserName(String username);
    UserDto findByUserEmail(String email);
    List<UserDto> findUserAll();
    List<UserDto> findDeletedUser();

}

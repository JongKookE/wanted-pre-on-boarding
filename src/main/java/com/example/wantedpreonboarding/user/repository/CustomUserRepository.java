package com.example.wantedpreonboarding.user.repository;

import com.example.wantedpreonboarding.user.entity.UserEntity;

import java.util.List;

public interface CustomUserRepository {
    void softDeleteByUserId(long userId);
    UserEntity findByUserName(String username);
    UserEntity findByUserEmail(String email);
    List<UserEntity> findUserAll();
    List<UserEntity> findDeletedUser();
}

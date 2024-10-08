package com.example.wantedpreonboarding.user.repository;

import com.example.wantedpreonboarding.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserName(String username);
    Optional<UserEntity> findByUserEmail(String email);
}

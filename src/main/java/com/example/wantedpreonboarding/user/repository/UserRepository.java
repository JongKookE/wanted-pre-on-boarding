package com.example.wantedpreonboarding.user.repository;

import com.example.wantedpreonboarding.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}

package com.example.wantedpreonboarding.user.repository;

import com.example.wantedpreonboarding.user.entity.UserEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.wantedpreonboarding.user.entity.QUserEntity.userEntity;

@Repository
@RequiredArgsConstructor
@Transactional
public class CustomUserRepositoryImpl implements CustomUserRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public void softDeleteByUserId(long userId) {
        queryFactory
                .update(userEntity)
                .where(userEntity.userId.eq(userId))
                .set(userEntity.deletedAt, LocalDateTime.now())
            .execute();
    }

    @Override
    public UserEntity findByUserName(String username) {
        return queryFactory
                .selectFrom(userEntity)
                .where(userEntity.deletedAt.isNull())
                .where(userEntity.userName.eq(username))
            .fetchFirst();
    }

    @Override
    public UserEntity findByUserEmail(String email) {
        return queryFactory
                .selectFrom(userEntity)
                .where(userEntity.deletedAt.isNull())
                .where(userEntity.userName.eq(email))
            .fetchFirst();
    }

    @Override
    public List<UserEntity> findUserAll() {
        return queryFactory
                .selectFrom(userEntity)
                .where(userEntity.deletedAt.isNull())
            .fetch();
    }

    @Override
    public List<UserEntity> findDeletedUser() {
        return queryFactory
                .selectFrom(userEntity)
                .where(userEntity.deletedAt.isNull())
            .fetch();
    }
}

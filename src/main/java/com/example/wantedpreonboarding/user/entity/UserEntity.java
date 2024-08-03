package com.example.wantedpreonboarding.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(nullable = false, unique = true)
    private String userEmail;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String userPhoneNumber;

    // 최초 생성 시간
    @CreatedDate
    private LocalDateTime createdAt;

    // 수정된 시간
    @LastModifiedDate
    private LocalDateTime updatedAt;

    // 삭제된 시간
    @Column
    private LocalDateTime deletedAt;
}

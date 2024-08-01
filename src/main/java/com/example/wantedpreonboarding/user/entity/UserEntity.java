package com.example.wantedpreonboarding.user.entity;

import jakarta.persistence.*;
import lombok.*;

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
}

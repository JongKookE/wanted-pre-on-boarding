package com.example.wantedpreonboarding.user.entity;

import com.example.wantedpreonboarding.post.entity.PostEntity;
import com.example.wantedpreonboarding.user.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "users")
@EntityListeners(AuditingEntityListener.class)
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

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PostEntity> posts;

    // 최초 생성 시간
    @CreatedDate
    private LocalDateTime createdAt;

    // 수정된 시간
    @LastModifiedDate
    private LocalDateTime updatedAt;

    // 삭제된 시간
    @Column
    private LocalDateTime deletedAt;


    public UserDto toDto(){
        return UserDto.builder()
                .userId(this.getUserId())
                .userEmail(this.getUserEmail())
                .userPassword(this.getUserPassword())
                .userName(this.getUserName())
                .userPhoneNumber(this.getUserPhoneNumber())
                .build();
    }
}

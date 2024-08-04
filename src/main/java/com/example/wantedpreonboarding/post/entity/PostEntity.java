package com.example.wantedpreonboarding.post.entity;

import com.example.wantedpreonboarding.post.dto.PostDto;
import com.example.wantedpreonboarding.user.dto.UserDto;
import com.example.wantedpreonboarding.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Entity(name = "post")
@Where(clause = "deleted_at IS NULL")
public class PostEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;

    @Column(nullable = false, unique = true, length = 200)
    private String postTitle;

    @Column(nullable = false, length = 1_000)
    private String postContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private UserEntity userEntity;

    // 최초 생성 시간
    @CreatedDate
    private LocalDateTime createdAt;

    // 수정된 시간
    @LastModifiedDate
    private LocalDateTime updatedAt;

    // 삭제된 시간
    @Column
    private LocalDateTime deletedAt;

    public PostDto toDto(){
        return PostDto.builder()
                .postId(this.postId)
                .postTitle(this.postTitle)
                .postContent(this.postContent)
                .userDto(this.userEntity.toDto())
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
            .build();
    }

}

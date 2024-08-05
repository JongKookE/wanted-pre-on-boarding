package com.example.wantedpreonboarding.post.entity;

import com.example.wantedpreonboarding.global.entity.BaseTimeEntity;
import com.example.wantedpreonboarding.post.dto.PostDto;
import com.example.wantedpreonboarding.user.dto.UserDto;
import com.example.wantedpreonboarding.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity(name = "post")
@Where(clause = "deleted_at IS NULL")
public class PostEntity extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;

    @Column(nullable = false, unique = true, length = 200)
    private String postTitle;

    @Column(nullable = false, length = 1_000)
    private String postContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private UserEntity userEntity;

    @Builder
    public PostEntity(LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, long postId, String postTitle, String postContent, UserEntity userEntity) {
        super(createdAt, updatedAt, deletedAt);
        this.postId = postId;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.userEntity = userEntity;
    }

    public PostDto toDto(){
        return PostDto.builder()
                .postId(this.postId)
                .postTitle(this.postTitle)
                .postContent(this.postContent)
                .userDto(this.userEntity.toDto())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
            .build();
    }

}

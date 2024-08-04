package com.example.wantedpreonboarding.post.dto;

import com.example.wantedpreonboarding.post.entity.PostEntity;
import com.example.wantedpreonboarding.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class PostDto {
    private long postId;

    private String postTitle;

    private String postContent;

    private UserDto userDto;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    public PostEntity toEntity(){
        return PostEntity.builder()
                .postTitle(this.postTitle)
                .postContent(this.postContent)
                .userEntity(this.userDto.toEntity())
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .deletedAt(this.deletedAt)
                .build();
    }
}

package com.example.wantedpreonboarding.post.repository;

import com.example.wantedpreonboarding.post.dto.PostDto;
import com.example.wantedpreonboarding.post.entity.PostEntity;

import java.util.List;

public interface CustomPostRepository {
    List<PostEntity> getAllDescendingPosts();
    List<PostEntity> getAllAscendingPosts();
    List<PostEntity> searchPost(String keyword);
    void softDeletePost(long userId, long postId);
    void hardDeletePost(long userId, long postId);
}

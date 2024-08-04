package com.example.wantedpreonboarding.post.service;

import com.example.wantedpreonboarding.post.dto.PostDto;
import com.example.wantedpreonboarding.post.entity.PostEntity;

import java.util.List;

public interface PostService {
    PostEntity savePost(PostDto postDto);
    PostEntity updatePost(PostDto postDto);
    List<PostDto> getAllDescendingPosts();
    List<PostDto> getAllAscendingPosts();
    List<PostDto> searchPost(String keyword);
    void softDeletePost(long userId, long postId);
    void hardDeletePost(long userId, long postId);
    PostDto findPostById(long postId);
}

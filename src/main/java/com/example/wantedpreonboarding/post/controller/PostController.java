package com.example.wantedpreonboarding.post.controller;

import com.example.wantedpreonboarding.post.dto.PostDto;
import com.example.wantedpreonboarding.post.entity.PostEntity;
import com.example.wantedpreonboarding.post.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Post", description = "게시글 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostEntity> savePost(@RequestBody PostDto postDto){
        return ResponseEntity.ok(postService.savePost(postDto));
    }

    @PutMapping
    public ResponseEntity<PostEntity> updatePost(@RequestBody PostDto postDto){
        return ResponseEntity.ok(postService.updatePost(postDto));
    }

    @GetMapping("/asc")
    public ResponseEntity<List<PostDto>> getAllDescendingPosts(){
        return ResponseEntity.ok(postService.getAllDescendingPosts());
    }

    @GetMapping("/desc")
    public ResponseEntity<List<PostDto>> getAllAscendingPosts(){
        return ResponseEntity.ok(postService.getAllAscendingPosts());
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> searchPost(@RequestParam String keyword){
        return ResponseEntity.ok(postService.searchPost(keyword));
    }

    @DeleteMapping("/soft")
    public ResponseEntity<?> softDeletePost(@RequestParam long userId, @RequestParam long postId){
        postService.softDeletePost(userId, postId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/hard")
    public ResponseEntity<?> hardDeletePost(@RequestParam long userId, @RequestParam long postId){
        postService.hardDeletePost(userId, postId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> findPostById(@PathVariable long postId){
        return ResponseEntity.ok(postService.findPostById(postId));
    }
}

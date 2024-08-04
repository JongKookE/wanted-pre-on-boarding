package com.example.wantedpreonboarding.post.service;

import com.example.wantedpreonboarding.post.dto.PostDto;
import com.example.wantedpreonboarding.post.entity.PostEntity;
import com.example.wantedpreonboarding.post.repository.CustomPostRepository;
import com.example.wantedpreonboarding.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final CustomPostRepository customPostRepository;

    @Override
    public PostEntity savePost(PostDto postDto) {
        return postRepository.save(postDto.toEntity());
    }

    @Override
    public PostEntity updatePost(PostDto postDto) {
        LocalDate now = LocalDate.now();

        // 두 날짜 사이의 기간 계산
        Period period = Period.between(now, postDto.getCreatedAt().toLocalDate());

        // 기간을 일(day) 단위로 변환
        int daysDifference = period.getDays() + (period.getMonths() * 30) + (period.getYears() * 365);

        // 10일 이상 차이나면 오류 발생
        if(Math.abs(daysDifference) >= 10) try {
            throw new Exception("수정 가능한 시간이 지났습니다.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return postRepository.save(postDto.toEntity());
    }

    @Override
    public List<PostDto> getAllDescendingPosts() {
        return customPostRepository.getAllDescendingPosts()
                .stream().map(PostEntity::toDto)
            .toList();
    }

    @Override
    public List<PostDto> getAllAscendingPosts() {
        return customPostRepository.getAllAscendingPosts()
                .stream().map(PostEntity::toDto)
            .toList();
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        return customPostRepository.searchPost(keyword)
                .stream().map(PostEntity::toDto)
            .toList();
    }

    @Override
    public void softDeletePost(long userId, long postId) {
        customPostRepository.softDeletePost(userId, postId);
    }

    @Override
    public void hardDeletePost(long userId, long postId) {
        customPostRepository.hardDeletePost(userId, postId);
    }

    @Override
    public PostDto findPostById(long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new RuntimeException("해당 게시글이 존재하지 않습니다.")).toDto();
    }
}

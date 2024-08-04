package com.example.wantedpreonboarding.post.repository;

import com.example.wantedpreonboarding.post.entity.PostEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import static com.example.wantedpreonboarding.post.entity.QPostEntity.postEntity;

@Repository
@RequiredArgsConstructor
public class CustomPostRepositoryImpl implements  CustomPostRepository{
    private final JPAQueryFactory queryFactory;
    private final PostRepository postRepository;

    @Override
    public List<PostEntity> getAllDescendingPosts() {
        return queryFactory
                .selectFrom(postEntity)
                .orderBy(postEntity.createdAt.desc())
                .stream()
            .toList();
    }

    @Override
    public List<PostEntity> getAllAscendingPosts() {
        return queryFactory
                .selectFrom(postEntity)
                .orderBy(postEntity.createdAt.asc())
                .stream()
            .toList();
    }

    @Override
    public List<PostEntity> searchPost(String keyword) {
        List<PostEntity> postEntities = queryFactory
                .selectFrom(postEntity)
                .where(postEntity.postTitle.like("%" + keyword + "%"))
                .orderBy(postEntity.postTitle.asc())
                .stream().toList();

        if (postEntities.isEmpty()) return this.getAllAscendingPosts();
        return postEntities;
    }

    @Override
    public void softDeletePost(long userId, long postId) {
        // 삭제하려는 사용자가 해당 글의 작성자가 아니라면 오류 발생
        long authorId = postRepository.findById(postId).orElseThrow(
                ()->new RuntimeException("post not found"))
            .getUserEntity().getUserId();
        if(authorId != userId) throw new RuntimeException("글의 소유자가 아닙니다.");

        queryFactory
                .update(postEntity)
                .where(postEntity.postId.eq(postId))
                .set(postEntity.deletedAt, LocalDateTime.now())
            .execute();
    }

    @Override
    public void hardDeletePost(long userId, long postId) {
        // 삭제하려는 사용자가 해당 글의 작성자가 아니라면 오류 발생
        long authorId = postRepository.findById(postId).orElseThrow(
                        ()->new RuntimeException("post not found"))
                .getUserEntity().getUserId();
        if(authorId != userId) throw new RuntimeException("글의 소유자가 아닙니다.");

        queryFactory
                .delete(postEntity)
                .where(postEntity.postId.eq(postId))
            .execute();
    }
}

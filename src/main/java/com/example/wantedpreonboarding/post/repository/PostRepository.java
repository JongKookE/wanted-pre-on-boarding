package com.example.wantedpreonboarding.post.repository;

import com.example.wantedpreonboarding.post.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

}

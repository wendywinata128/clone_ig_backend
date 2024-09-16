package com.commsult_test.clone_ig.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commsult_test.clone_ig.model.PostLike;
import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    public Optional<PostLike> findByUserUsernameAndPostId(String username, Long postId);
}

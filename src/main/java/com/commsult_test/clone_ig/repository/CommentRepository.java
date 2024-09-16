package com.commsult_test.clone_ig.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commsult_test.clone_ig.model.Comment;
import java.util.List;

@Repository
public interface CommentRepository  extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostIdOrderByCreateddtDesc(Long postId);

    Comment findByIdAndUserId(Long id, Long userId);
}

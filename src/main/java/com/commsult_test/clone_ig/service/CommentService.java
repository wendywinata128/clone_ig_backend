package com.commsult_test.clone_ig.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commsult_test.clone_ig.exceptions.ResponseException;
import com.commsult_test.clone_ig.model.Comment;
import com.commsult_test.clone_ig.model.Post;
import com.commsult_test.clone_ig.model.User;
import com.commsult_test.clone_ig.repository.CommentRepository;
import com.commsult_test.clone_ig.repository.PostRepository;

@Service
public class CommentService {

    @Autowired
    UserService userService;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    public List<Comment> getCommentOfPost(Long postId) {
        return commentRepository.findAllByPostIdOrderByCreateddtDesc(postId);
    }

    public Comment addComment(Long postId, String description) throws ResponseException {
        User user = userService.getUser();
        Optional<Post> post = postRepository.findById(postId);

        if (!post.isPresent()) {
            throw new ResponseException("Post not found", 400, postId);
        }

        Comment comment = Comment.builder()
                .description(description)
                .user(user)
                .post(post.get())
                .build();

        return commentRepository.save(comment);
    }

    public Comment deleteComment(Long commentId) throws ResponseException {
        User user = userService.getUser();
        Comment comment = commentRepository.findByIdAndUserId(commentId, user.getId());

        if (comment == null) {
            throw new ResponseException("Comment not found", 400, commentId);
        }

        commentRepository.delete(comment);

        return comment;
    }
}

package com.commsult_test.clone_ig.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commsult_test.clone_ig.dto.BasicResponse;
import com.commsult_test.clone_ig.dto.CommentRequestDTO;
import com.commsult_test.clone_ig.exceptions.ResponseException;
import com.commsult_test.clone_ig.model.Comment;
import com.commsult_test.clone_ig.service.CommentService;

@RestController
@RequestMapping("comments")
public class CommentController extends BasicController {

    @Autowired
    CommentService commentService;

    @GetMapping("/{postId}")
    public ResponseEntity<BasicResponse<Object>> getCommentOfPost(@PathVariable String postId) {
        List<Comment> comments = commentService.getCommentOfPost(Long.parseLong(postId));

        return respondSuccess(comments);
    }


    @PostMapping("/{postId}")
    public ResponseEntity<BasicResponse<Object>> createNewComment(@PathVariable String postId,
            @RequestBody CommentRequestDTO commentRequestDTO) throws NumberFormatException, ResponseException {
        Comment comment = commentService.addComment(Long.parseLong(postId), commentRequestDTO.getDescription());

        return respondSuccess(comment);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<BasicResponse<Object>> deleteComment(@PathVariable String postId) throws NumberFormatException, ResponseException {
        Comment comment = commentService.deleteComment(Long.parseLong(postId));

        return respondSuccess(comment);
    }
}

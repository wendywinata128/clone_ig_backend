package com.commsult_test.clone_ig.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commsult_test.clone_ig.dto.BasicResponse;
import com.commsult_test.clone_ig.dto.PostRequestDTO;
import com.commsult_test.clone_ig.exceptions.ResponseException;
import com.commsult_test.clone_ig.dto.PostDetailsDTO;
import com.commsult_test.clone_ig.model.Post;
import com.commsult_test.clone_ig.service.PostService;
import com.commsult_test.clone_ig.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;



@RestController
@RequestMapping("posts")
public class PostController extends BasicController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @GetMapping("/home")
    public ResponseEntity<BasicResponse<Page<PostDetailsDTO>>> getUserFollowingPost(Pageable pageable){

        Page<PostDetailsDTO> postsData = postService.getPostBasedOnUserFollowing(pageable);

        return respondSuccess(postsData);
    }

    @GetMapping("/users")
    public ResponseEntity<BasicResponse<List<Post>>> getCurrentUserPost(){

        List<Post> postsData = postService.getUserPostData();

        return respondSuccess(postsData);
    }

    @PostMapping("/")
    public ResponseEntity<BasicResponse<Post>> createUserPost(@Valid @ModelAttribute PostRequestDTO body) throws IOException {
        Post post = postService.createUserPost(body);
        return respondSuccess(post);
    }

    @PatchMapping("/{postId}/likes")
    public ResponseEntity<BasicResponse<Object>> likePostByCurrentUser(@PathVariable String postId) throws NumberFormatException, ResponseException {
        String messageResult = postService.toggleUserPostLike(Long.parseLong(postId));

        return respondSuccess(messageResult);
    }

}

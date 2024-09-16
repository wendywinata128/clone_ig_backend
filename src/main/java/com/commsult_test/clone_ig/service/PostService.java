package com.commsult_test.clone_ig.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.commsult_test.clone_ig.dto.PostRequestDTO;
import com.commsult_test.clone_ig.exceptions.ResponseException;
import com.commsult_test.clone_ig.dto.PostDetailsDTO;
import com.commsult_test.clone_ig.model.Post;
import com.commsult_test.clone_ig.model.PostLike;
import com.commsult_test.clone_ig.model.User;
import com.commsult_test.clone_ig.repository.PostLikeRepository;
import com.commsult_test.clone_ig.repository.PostRepository;
import com.commsult_test.clone_ig.utils.Constant;
import com.commsult_test.clone_ig.utils.FileUploadUtil;
import com.commsult_test.clone_ig.utils.FunctionUtil;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;

    @Autowired
    PostLikeRepository postLikeRepository;

    public Page<PostDetailsDTO> getPostBasedOnUserFollowing(Pageable pageable) {
        User user = userService.getUser();
        Page<PostDetailsDTO> post = postRepository.getPostDetailsData(user.getId(), pageable);

        return post;
    }

    @Transactional
    public Post createUserPost(PostRequestDTO postRequestDTO) throws IOException {
        // validasi extension nanti...;

        User user = userService.getUser();

        String fileName = user.getUsername() + "-post-" + FunctionUtil.getCurrentDateInStringFormatted();

        String savedPath = FileUploadUtil.uploadFile(postRequestDTO.getPhoto(), fileName, Constant.FOLDER_POST_PATH);

        Post post = Post.builder()
                .description(postRequestDTO.getDescription())
                .photo_url(savedPath)
                .user(user)
                .build();

        postRepository.save(post);

        return post;
    }

    @Transactional
    public Post createUserPost(PostRequestDTO postRequestDTO, User user, String savedPath) throws IOException {
        Post post = Post.builder()
                .description(postRequestDTO.getDescription())
                .photo_url(savedPath)
                .user(user)
                .build();

        postRepository.save(post);

        return post;
    }

    @Transactional
    public String toggleUserPostLike(Long id) throws ResponseException {
        User user = userService.getUser();
        Optional<Post> post = postRepository.findById(id);

        if (!post.isPresent()) {
            throw new ResponseException("Post not found", 400, id);
        }

        Optional<PostLike> postLike = postLikeRepository.findByUserUsernameAndPostId(user.getUsername(),
                post.get().getId());

        if (postLike.isPresent()) {
            postLikeRepository.delete(postLike.get());
            return user.getName() + " Unlike the post";
        } else {
            PostLike postLikeNew = PostLike.builder()
                    .user(user)
                    .post(post.get())
                    .build();

            postLikeRepository.save(postLikeNew);
            return user.getName() + " Like the post";
        }

    }
}

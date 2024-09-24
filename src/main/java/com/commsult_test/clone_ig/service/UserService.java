package com.commsult_test.clone_ig.service;

import java.io.IOException;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.commsult_test.clone_ig.dto.DummyStoryDto;
import com.commsult_test.clone_ig.dto.LoginRequestDTO;
import com.commsult_test.clone_ig.dto.PostDetailsDTO;
import com.commsult_test.clone_ig.dto.UserDetailDTO;
import com.commsult_test.clone_ig.dto.UserDetailResponseDTO;
import com.commsult_test.clone_ig.dto.UserRequestDTO;
import com.commsult_test.clone_ig.exceptions.ResponseException;
import com.commsult_test.clone_ig.model.User;
import com.commsult_test.clone_ig.model.UserFollow;
import com.commsult_test.clone_ig.repository.PostRepository;
import com.commsult_test.clone_ig.repository.UserFollowRepository;
import com.commsult_test.clone_ig.repository.UserRepository;
import com.commsult_test.clone_ig.utils.Constant;
import com.commsult_test.clone_ig.utils.FileUploadUtil;
import com.commsult_test.clone_ig.utils.FunctionUtil;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserFollowRepository userFollowRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public User register(UserRequestDTO userRequest) throws IOException, ResponseException {

        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new ResponseException("Username has been taken", 400, userRequest.getUsername());
        }

        String pathAvatar = userRequest.getUsername() + ".jpg";

        if (userRequest.getAvatar() != null) {
            String fileName = userRequest.getUsername() + "-user-" + FunctionUtil.getCurrentDateInStringFormatted();

            pathAvatar = FileUploadUtil.uploadFile(userRequest.getAvatar(), fileName, Constant.FOLDER_USER_PATH);
        }

        User user = User.builder()
                .name(userRequest.getName())
                .avatar(pathAvatar)
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .role(userRequest.getRole())
                .build();

        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        User userResult = userRepository.save(user);

        return userResult;
    }

    public String login(LoginRequestDTO loginRequestDTO) throws ResponseException {
        User user = userRepository.findByUsername(loginRequestDTO.getUsername());

        if(user == null){
            throw new ResponseException("Authentication Failed", 401, user);
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));

        return jwtService.generateToken(user);
    }

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return (User) authentication.getPrincipal();
    }

    public UserDetailResponseDTO getUserDetail(String username) throws ResponseException {

        User currUser = getUser();
        UserDetailDTO user = userRepository.getUserDetails(username, currUser.getUsername());

        if (user == null) {
            throw new ResponseException("Username not found", 400, username);
        }

        Pageable pageable = PageRequest.of(0, 30, Sort.by(Sort.Order.desc("createddt")));

        List<PostDetailsDTO> posts = postRepository.getUserPostData(user.getId(), pageable).getContent();

        UserDetailResponseDTO response = UserDetailResponseDTO
                .builder()
                .user(user)
                .posts(posts)
                .build();

        return response;
    }

    public List<User> searchUser(String keyword) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<User> users = userRepository.findByUsernameLike(keyword, pageable);

        return users.getContent();
    }

    public void followUser(Long userId) throws ResponseException {
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new ResponseException("User id not found", 400, userId);
        }

        User currentUser = getUser();

        UserFollow userFollow = UserFollow.builder()
                .follow(user.get())
                .user(currentUser)
                .build();

        userFollowRepository.save(userFollow);
    }

    public void followUser(User currentUser, Long userId) throws ResponseException {
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new ResponseException("User id not found", 400, userId);
        }

        UserFollow userFollow = UserFollow.builder()
                .follow(user.get())
                .user(currentUser)
                .build();

        userFollowRepository.save(userFollow);
    }

    @Transactional
    public void unfollowUser(Long userId) throws ResponseException {
        User currentUser = getUser();

        userFollowRepository.deleteAllByUserIdAndFollowId(currentUser.getId(), userId);
    }

    public List<DummyStoryDto> getUserStoriesDummy() throws ResponseException {
        User currentUser = getUser();

        List<DummyStoryDto> userStories = userRepository.getDummyStories(currentUser.getUsername());

        return userStories;

    }

}

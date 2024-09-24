package com.commsult_test.clone_ig.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.commsult_test.clone_ig.dto.BasicResponse;
import com.commsult_test.clone_ig.dto.DummyStoryDto;
import com.commsult_test.clone_ig.dto.LoginRequestDTO;
import com.commsult_test.clone_ig.dto.UserDetailResponseDTO;
import com.commsult_test.clone_ig.dto.UserRequestDTO;
import com.commsult_test.clone_ig.exceptions.ResponseException;
import com.commsult_test.clone_ig.model.User;
import com.commsult_test.clone_ig.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;



@RestController
@RequestMapping("user")
public class UserController extends BasicController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<BasicResponse<User>> register(@Valid @ModelAttribute UserRequestDTO userRequest) throws IOException, ResponseException{

        User user = userService.register(userRequest);

        return respondSuccess(user);
    }

    @PostMapping("/login")
    public ResponseEntity<BasicResponse<String>> login(@Valid @RequestBody LoginRequestDTO loginRequestDto, HttpServletResponse response) throws ResponseException {

        String token = userService.login(loginRequestDto);

        return respondSuccess(token);
    }

    @GetMapping("")
    public ResponseEntity<BasicResponse<User>> user() {
        User user = userService.getUser();

        return respondSuccess(user);
    }

    @GetMapping("search/{keyword}")
    public ResponseEntity<BasicResponse<List<User>>> searchUser(@PathVariable String keyword) {
        List<User> users = userService.searchUser(keyword);

        return respondSuccess(users);
    }

    @GetMapping("/{username}")
    public ResponseEntity<BasicResponse<UserDetailResponseDTO>> userDetails(@PathVariable String username) throws ResponseException {
        UserDetailResponseDTO user = userService.getUserDetail(username);

        return respondSuccess(user);
    }


    @PostMapping("/{userId}/follow")
    public ResponseEntity<BasicResponse<User>> follow(@PathVariable String userId) throws NumberFormatException, ResponseException {
        userService.followUser(Long.parseLong(userId));
        
        return respondSuccess(null);
    }

    @DeleteMapping("/{userId}/unfollow")
    public ResponseEntity<BasicResponse<User>> unfollow(@PathVariable String userId) throws NumberFormatException, ResponseException {
        userService.unfollowUser(Long.parseLong(userId));
        
        return respondSuccess(null);
    }

    @GetMapping("/stories")
    public ResponseEntity<BasicResponse<List<DummyStoryDto>>> stories() throws NumberFormatException, ResponseException {
        var stories = userService.getUserStoriesDummy();
        
        return respondSuccess(stories);
    }

}

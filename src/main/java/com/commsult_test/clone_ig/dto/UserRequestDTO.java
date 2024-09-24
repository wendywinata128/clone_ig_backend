package com.commsult_test.clone_ig.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDTO {
    @NotNull
    String username;

    @NotNull
    String name;

    @NotNull
    @Email
    String email;

    MultipartFile avatar;

    String role;

    @NotNull
    String password;
}

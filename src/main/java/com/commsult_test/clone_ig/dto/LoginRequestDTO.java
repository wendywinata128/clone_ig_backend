package com.commsult_test.clone_ig.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequestDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;
}

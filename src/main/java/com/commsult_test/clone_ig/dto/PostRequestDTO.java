package com.commsult_test.clone_ig.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostRequestDTO {

    @NotNull
    MultipartFile photo;

    @NotNull
    String description;
}

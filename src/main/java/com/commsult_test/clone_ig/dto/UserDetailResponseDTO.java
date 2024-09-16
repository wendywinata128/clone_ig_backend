package com.commsult_test.clone_ig.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDetailResponseDTO {
    UserDetailDTO user;

    List<PostDetailsDTO> posts;
    
}

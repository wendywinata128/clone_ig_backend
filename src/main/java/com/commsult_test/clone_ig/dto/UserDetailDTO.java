package com.commsult_test.clone_ig.dto;

public interface UserDetailDTO {
    Long getId();
    Long getFollowers();
    Long getFollowing();
    Long getPosts();
    Long getIsFollow();
    String getEmail();
    String getUsername();
    String getName();
    String getAvatar();
    java.util.Date getCreateddt();
    java.util.Date getUpdateddt();

    
}

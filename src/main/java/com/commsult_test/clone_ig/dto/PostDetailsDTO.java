package com.commsult_test.clone_ig.dto;

import java.util.Date;

public interface PostDetailsDTO {

    String getAvatar();

    String getUsername();
    
    Long getId();

    String getPhotoUrl();

    String getDescription();

    Date getCreateddt();

    long getLikes();

    long getComments();

    long getUserLike();
}

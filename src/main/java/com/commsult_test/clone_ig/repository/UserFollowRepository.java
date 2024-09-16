package com.commsult_test.clone_ig.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commsult_test.clone_ig.model.UserFollow;

@Repository
public interface UserFollowRepository extends JpaRepository<UserFollow, Long>{
    UserFollow findByUserIdAndFollowId(Long userId, Long followUserId);

    void deleteAllByUserIdAndFollowId(Long userId, Long followUserId);
}

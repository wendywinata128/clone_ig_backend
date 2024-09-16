package com.commsult_test.clone_ig.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.commsult_test.clone_ig.dto.DummyStoryDto;
import com.commsult_test.clone_ig.dto.UserDetailDTO;
import com.commsult_test.clone_ig.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String email);

    @Query("SELECT u from User u WHERE u.username LIKE %:keyword%  OR u.name LIKE %:keyword%")
    Page<User> findByUsernameLike(String keyword, Pageable pageable);

    @Query("SELECT u.name as name, u.username as username, u.id as id, u.email as email, u.avatar as avatar, u.createddt as createddt, u.updateddt as updateddt," +
       " COUNT(DISTINCT following.id) AS following, " +
       " COUNT(DISTINCT followers.id) AS followers, " +
       " COUNT(DISTINCT p.id) AS posts, " +
       " (SELECT COUNT(f) from UserFollow f where f.user.username = :currUsername AND f.follow.username = :username ) AS isFollow" +
       " FROM User u " +
       " LEFT JOIN UserFollow followers ON followers.follow.id = u.id " +
       " LEFT JOIN UserFollow following ON following.user.id = u.id " +
       " LEFT JOIN Post p ON p.user.id = u.id " +
       " WHERE u.username = :username " +
       " GROUP BY u.id")
    UserDetailDTO getUserDetails(String username, String currUsername);

    @Query("SELECT  uf.follow.name as name, uf.follow.username as username, uf.follow.avatar as avatar from User u JOIN UserFollow uf on (uf.user.id = u.id) where u.username = :username")
    List<DummyStoryDto> getDummyStories(String username);


}

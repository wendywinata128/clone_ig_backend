package com.commsult_test.clone_ig.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.commsult_test.clone_ig.dto.PostDetailsDTO;
import com.commsult_test.clone_ig.model.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUserUsername(String username);

    Post findByIdAndUserUsername(Long id, String username);

    @Query("select p.user.avatar as avatar, p.user.username as username,  p.id as id, p.photo_url as photoUrl , p.description as description , p.createddt as createddt ,"
    + " (select count(c) from Comment c where c.post.id = p.id) as comments,"
    + " (select count(pl) from PostLike pl where pl.post.id = p.id) as likes,"
    + " (select count(pl) from PostLike pl where pl.post.id = p.id and pl.user.id = :id) as userLike"
    + " from Post p where p.user.id = :id OR  p.user IN" 
        + " (select uf.follow from UserFollow uf where uf.user.id = :id )")
    Page<PostDetailsDTO> getPostDetailsData(@Param("id") Long id, Pageable pageable);

    @Query("select p.user.avatar as avatar, p.user.username as username,  p.id as id, p.photo_url as photoUrl , p.description as description , p.createddt as createddt ,"
    + " (select count(c) from Comment c where c.post.id = p.id) as comments,"
    + " (select count(pl) from PostLike pl where pl.post.id = p.id) as likes,"
    + " (select count(pl) from PostLike pl where pl.post.id = p.id and pl.user.id = :id) as userLike"
    + " from Post p where p.user.id = :id")
    Page<PostDetailsDTO> getUserPostData(@Param("id") Long id, Pageable pageable);

    // retrieve all post by user followings
    // List<Post> getUserHomePost();
}

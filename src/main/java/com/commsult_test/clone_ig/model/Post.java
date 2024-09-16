package com.commsult_test.clone_ig.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
@Builder
@Entity
public class Post extends BasicModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    

    private String photo_url;

    private String description;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "post")
    private List<PostLike> postLikes;

}

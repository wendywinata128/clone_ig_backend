package com.commsult_test.clone_ig.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stories")
@Builder
@Entity
public class Story extends BasicModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    

    private String photo_url;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}

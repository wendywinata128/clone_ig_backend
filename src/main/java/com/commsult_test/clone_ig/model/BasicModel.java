package com.commsult_test.clone_ig.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BasicModel {
    @CreationTimestamp
    private Date createddt;

    @UpdateTimestamp
    private Date updateddt;
}

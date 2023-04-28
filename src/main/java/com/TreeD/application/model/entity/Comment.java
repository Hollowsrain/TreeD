package com.TreeD.application.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {

    @ManyToOne
    private User user;

    @Column(name = "rating")
    private Double rating;

    @ManyToOne
    private Model model;

    @Column(name = "comment")
    @Lob
    private String comment;
}

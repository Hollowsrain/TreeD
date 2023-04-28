package com.TreeD.application.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "model")
public class Model extends BaseEntity {

    @ManyToOne
    private User user;

    @Column(name = "name", nullable = false)
    private String modelName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency"))
    })
    private Price price;

    @Column(name = "image", nullable = false)
    @Lob
    private Byte[] image;

    @Column
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "model")
    private List<Comment> comments;

    @Column(name = "details", columnDefinition = "TEXT default ' '")
    @Lob
    private String details;

    @Column(name = "model", nullable = false)
    @Lob
    private Byte[] model;

    @OneToOne
    private Vehicle carModel;
}

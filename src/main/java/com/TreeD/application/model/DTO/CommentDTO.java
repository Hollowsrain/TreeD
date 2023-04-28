package com.TreeD.application.model.DTO;

import com.TreeD.application.model.entity.Model;
import com.TreeD.application.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.ZonedDateTime;

@NoArgsConstructor
@Getter
@Setter
public class CommentDTO {

    private Long id;

    private ZonedDateTime createdAt;

    private ZonedDateTime modifiedAt;

    private User user;

    private Double rating;

    private Model model;

    private String comment;
}
package com.TreeD.application.service;

import com.TreeD.application.model.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentsForModel(Long id);

    List<Comment> getUserCommentsByUserId(Long id);

    Comment getCommentById(Long id);

    Comment createComment(Long userId, Comment comment);

    Comment updateCommentById(Long id, Comment comment);

    void deleteCommentById(Long id);
}

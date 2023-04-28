package com.TreeD.application.service.implementation;

import com.TreeD.application.exceptions.CommentNotFoundException;
import com.TreeD.application.model.entity.Comment;
import com.TreeD.application.model.entity.User;
import com.TreeD.application.repository.CommentRepository;
import com.TreeD.application.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserServiceImpl userService;

    public CommentServiceImpl(CommentRepository commentRepository, UserServiceImpl userService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    @Override
    public List<Comment> getCommentsForModel(Long id) {
        return commentRepository.findAllByModelId(id);
    }

    @Override
    public List<Comment> getUserCommentsByUserId(Long id) {
        return commentRepository.findAllByUserId(id);
    }

    @Override
    public Comment getCommentById(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);

        if (comment.isEmpty()) {
            log.error("Unable to find comment for id: " + id);
            throw new CommentNotFoundException("Unable to find comment for given id");
        }
        return comment.get();
    }

    @Override
    public Comment createComment(Long userId, Comment comment) {
        User user = userService.getUserById(userId);



        comment.setUser(user);
        comment.setId(null);
        comment.setCreatedAt(ZonedDateTime.now());
        comment.setModifiedAt(ZonedDateTime.now());
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateCommentById(Long id, Comment comment) {
        Comment currentComment = getCommentById(id);

        comment.setId(currentComment.getId());
        comment.setModifiedAt(ZonedDateTime.now());

        return commentRepository.save(comment);
    }

    @Override
    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }
}

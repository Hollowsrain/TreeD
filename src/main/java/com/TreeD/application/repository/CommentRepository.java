package com.TreeD.application.repository;

import com.TreeD.application.model.entity.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {


    List<Comment> findAllByUserId(Long userId);

    List<Comment> findAllByModelId(Long modelId);
}

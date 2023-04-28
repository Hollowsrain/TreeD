package com.TreeD.application.model.converter;

import com.TreeD.application.model.DTO.CommentDTO;
import com.TreeD.application.model.entity.Comment;
import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentToCommentDTO implements Converter<Comment, CommentDTO> {

    @Synchronized
    @Nullable
    @Override
    public CommentDTO convert(Comment source) {
        if (source == null) {
            return null;
        }

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(source.getId());
        commentDTO.setComment(source.getComment());
        commentDTO.setUser(source.getUser());
        commentDTO.setModel(source.getModel());
        commentDTO.setRating(source.getRating());
        commentDTO.setCreatedAt(source.getCreatedAt());
        commentDTO.setModifiedAt(source.getModifiedAt());

        return commentDTO;
    }
}
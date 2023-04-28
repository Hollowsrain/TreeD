package com.TreeD.application.model.converter;

import com.TreeD.application.model.DTO.CommentDTO;
import com.TreeD.application.model.entity.Comment;
import com.TreeD.application.util.ApplicationUtils;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentDTOToComment implements Converter<CommentDTO, Comment> {

    private final ApplicationUtils applicationUtils;

    @Synchronized
    @Nullable
    @Override
    public Comment convert(CommentDTO source) {
        if (source == null) {
            return null;
        }

        Comment comment = new Comment();
        comment.setId(source.getId());
        comment.setComment(source.getComment());
        comment.setUser(applicationUtils.getCurrentUser());
        comment.setModel(source.getModel());
        comment.setRating(source.getRating());
        comment.setCreatedAt(source.getCreatedAt());
        comment.setModifiedAt(source.getModifiedAt());

        return comment;
    }
}

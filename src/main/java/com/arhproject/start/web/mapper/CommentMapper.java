package com.arhproject.start.web.mapper;

import com.arhproject.start.jpa.domain.Comment;
import com.arhproject.start.web.dto.comment.CommentModel;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentModel toModel(Comment comment) {
        return CommentModel.builder()
                .id(comment.getId())
                .publicationId(comment.getPublicationId())
                .userId(comment.getUser().getId())
                .text(comment.getText())
                .createdTime(comment.getAudit().getCreatedTime())
                .lastModifiedTime(comment.getAudit().getLastModifiedTime())
                .build();
    }
}

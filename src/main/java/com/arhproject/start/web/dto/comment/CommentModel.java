package com.arhproject.start.web.dto.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CommentModel {
    private Long id;
    private Long publicationId;
    private Long userId;
    private String text;
    private LocalDateTime createdTime;
    private LocalDateTime lastModifiedTime;
}

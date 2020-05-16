package com.arhproject.start.web.mapper;

import com.arhproject.start.jpa.domain.Comment;
import com.arhproject.start.jpa.domain.Publication;
import com.arhproject.start.jpa.domain.Tag;
import com.arhproject.start.jpa.domain.User;
import com.arhproject.start.web.dto.publication.PublicationModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PublicationMapper {

    private final TextOrImageMapper textOrImageMapper;

    public PublicationModel toModel(Publication publication) {
        return PublicationModel.builder()
                .id(publication.getId())
                .title(publication.getTitle())
                .textOrImageModels(publication.getTextOrImages().stream()
                        .map(textOrImageMapper::toModel)
                        .collect(Collectors.toList()))
                .userIds(publication.getAuthors().stream()
                        .map(User::getId)
                        .collect(Collectors.toList()))
                .tags(publication.getTags().stream()
                        .map(Tag::getName)
                        .collect(Collectors.toList()))
                .commentIds(publication.getComments().stream()
                        .map(Comment::getId)
                        .collect(Collectors.toList()))
                .createdTime(publication.getAudit().getCreatedTime())
                .lastModifiedTime(publication.getAudit().getLastModifiedTime())
                .build();
    }

}

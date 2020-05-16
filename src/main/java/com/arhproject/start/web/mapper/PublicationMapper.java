package com.arhproject.start.web.mapper;

import com.arhproject.start.jpa.domain.Publication;
import com.arhproject.start.web.dto.publication.PublicationModel;
import com.arhproject.start.web.dto.publication.PublicationShortModel;
import org.springframework.stereotype.Component;

@Component
public class PublicationMapper {

    public PublicationModel toModel(Publication publication) {
        return PublicationModel.builder()
                .id(publication.getId())
                .title(publication.getTitle())

                .build();
    }

    public PublicationShortModel toShortModel(Publication publication) {
        return PublicationShortModel.builder()
                .id(publication.getId())
                .title(publication.getTitle())
                .build();
    }
}

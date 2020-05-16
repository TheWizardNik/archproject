package com.arhproject.start.web.mapper;

import com.arhproject.start.jpa.domain.TextOrImage;
import com.arhproject.start.web.dto.publication.TextOrImageModel;
import org.springframework.stereotype.Component;

@Component
public class TextOrImageMapper {
    public TextOrImageModel toModel(TextOrImage textOrImage) {
        return TextOrImageModel.builder()
                .body(textOrImage.getBody())
                .isText(textOrImage.getIsText())
                .build();
    }
}

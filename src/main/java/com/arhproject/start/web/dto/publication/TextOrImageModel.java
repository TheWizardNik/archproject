package com.arhproject.start.web.dto.publication;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class TextOrImageModel {
    private String body;
    private Boolean isText;
}

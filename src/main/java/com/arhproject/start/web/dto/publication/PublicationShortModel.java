package com.arhproject.start.web.dto.publication;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PublicationShortModel {
    private Long id;
    private String title;
    private String text;
}

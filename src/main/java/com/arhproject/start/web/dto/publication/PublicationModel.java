package com.arhproject.start.web.dto.publication;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class PublicationModel {
    private Long id;
    private List<Long> userIds = new ArrayList<>();
    private List<TextOrImageModel> textOrImageModels = new ArrayList<>();
    private String title;
    private List<String> tags = new ArrayList<>();
    private List<Long> commentIds = new ArrayList<>();
    private LocalDateTime createdTime;
    private LocalDateTime lastModifiedTime;
}

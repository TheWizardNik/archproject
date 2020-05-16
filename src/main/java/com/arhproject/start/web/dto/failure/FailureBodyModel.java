package com.arhproject.start.web.dto.failure;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class FailureBodyModel {

    private Instant timestamp;

    private Integer status;

    private String error;

    private String message;

    private String path;

    private Map<String, List<String>> validationErrors;
}

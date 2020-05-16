package com.arhproject.start.web.mapper;

import com.arhproject.start.web.dto.failure.FailureBodyModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Component
public class FailureBodyMapper {
    public FailureBodyModel toModel(String message, HttpStatus status, HttpServletRequest request, Map<String, List<String>> validationErrors) {
        return FailureBodyModel.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(getPath(request))
                .validationErrors(validationErrors)
                .build();
    }

    public FailureBodyModel toModel(String message, HttpStatus status, HttpServletRequest request) {
        return FailureBodyModel.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(getPath(request))
                .build();
    }

    private String getPath(HttpServletRequest request) {
        try {
            return new URI(request.getRequestURI()).getPath();
        } catch (URISyntaxException e) {
            return null;
        }
    }
}

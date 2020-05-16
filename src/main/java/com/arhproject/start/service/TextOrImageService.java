package com.arhproject.start.service;

import com.arhproject.start.jpa.domain.Publication;
import com.arhproject.start.jpa.domain.TextOrImage;
import com.arhproject.start.jpa.repository.TextOrImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TextOrImageService {

    private final TextOrImageRepository textOrImageRepository;

    public TextOrImage create(String body, Boolean isText, Publication publication) {
        TextOrImage textOrImage = new TextOrImage();
        textOrImage.setBody(body);
        textOrImage.setIsText(isText);
        textOrImage.setPublication(publication);
        return textOrImageRepository.save(textOrImage);
    }
}

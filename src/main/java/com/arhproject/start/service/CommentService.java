package com.arhproject.start.service;

import com.arhproject.start.jpa.domain.Comment;
import com.arhproject.start.jpa.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PublicationService publicationService;

    public Comment get(Long id) {
        return commentRepository.getOne(id);
    }

    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    public Comment create(Long publicationId, String text) {
        return publicationService.find(publicationId)
                .map(publication -> create(publicationId, text))
                .orElseThrow(() -> new EntityNotFoundException("Publication not found"));
    }
}

package com.arhproject.start.service;

import com.arhproject.start.jpa.domain.Publication;
import com.arhproject.start.jpa.repository.PublicationRepository;
import com.arhproject.start.web.dto.publication.CreatePublicationDto;
import com.arhproject.start.web.dto.publication.UpdatePublicationDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PublicationService {

    private final PublicationRepository publicationRepository;

    public Optional<Publication> find(Long id) {
        return publicationRepository.findById(id);
    }

    public Publication create(CreatePublicationDto createDto) {
        Publication publication = new Publication();
        publication.setTitle(createDto.getTitle());
        return publicationRepository.save(publication);
    }

    public Publication get(Long id) {
        return publicationRepository.getOne(id);
    }

    public List<Publication> getAll() {
        return publicationRepository.findAll();
    }

    public void delete(Long id) {
        publicationRepository.deleteById(id);
    }

    public Publication update(Long id, UpdatePublicationDto updateDto) {
        Publication publication = publicationRepository.getOne(id);
        publication.setTitle(updateDto.getTitle());
        return publicationRepository.save(publication);
    }
}

package com.arhproject.start.service;

import com.arhproject.start.jpa.domain.Publication;
import com.arhproject.start.jpa.repository.PublicationRepository;
import com.arhproject.start.jpa.repository.TagRepository;
import com.arhproject.start.jpa.repository.UserRepository;
import com.arhproject.start.web.dto.publication.CreatePublicationDto;
import com.arhproject.start.web.dto.publication.UpdatePublicationDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PublicationService {

    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final TagService tagService;
    private final TextOrImageService textOrImageService;

    public Optional<Publication> find(Long id) {
        return publicationRepository.findById(id);
    }

    public Publication create(CreatePublicationDto createDto) {
        createTagsIfNotExist(createDto.getTags());

        Publication publication = new Publication();
        publication.setTitle(createDto.getTitle());
        publication.setAuthors(userRepository.findAllById(createDto.getUserIds()));
        publication.setTags(tagRepository.findAllByNameIn(createDto.getTags()));
        Publication sandedPublication = publicationRepository.save(publication);
        publication.setTextOrImages(createDto.getTextOrImageModel().stream()
                .map(f -> textOrImageService.create(f.getBody(), f.getIsText(), sandedPublication))
                .collect(Collectors.toList()));
        return sandedPublication;
    }

    private void createTagsIfNotExist(List<String> tagNames) {
        for (String tag : tagNames) {
            if (!tagRepository.findByName(tag).isPresent()) {
                tagService.create(tag);
            }
        }
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
        createTagsIfNotExist(updateDto.getTags());

        Publication publication = publicationRepository.getOne(id);
        publication.setTitle(updateDto.getTitle());
        publication.setTextOrImages(updateDto.getTextOrImageModel().stream()
                .map(f -> textOrImageService.create(f.getBody(), f.getIsText(), publication))
                .collect(Collectors.toList()));
        publication.setAuthors(userRepository.findAllById(updateDto.getUserIds()));
        publication.setTags(tagRepository.findAllByNameIn(updateDto.getTags()));
        return publicationRepository.save(publication);
    }
}

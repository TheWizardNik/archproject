package com.arhproject.start.service;

import com.arhproject.start.jpa.domain.Tag;
import com.arhproject.start.jpa.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public Tag create(String name) {
        Tag tag = new Tag();
        tag.setName(name);
        return tagRepository.save(tag);
    }

    public Tag get(Long id) {
        return tagRepository.getOne(id);
    }

    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    public Tag update(Long id, String name) {
        Tag tag = tagRepository.getOne(id);
        tag.setName(name);
        return tagRepository.save(tag);
    }

    public void delete(Long id) {
        tagRepository.deleteById(id);
    }
}
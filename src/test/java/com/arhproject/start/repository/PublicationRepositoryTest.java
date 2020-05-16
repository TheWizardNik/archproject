package com.arhproject.start.repository;

import com.arhproject.start.jpa.domain.Publication;
import com.arhproject.start.jpa.domain.Tag;
import com.arhproject.start.jpa.domain.TextOrImage;
import com.arhproject.start.jpa.repository.PublicationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PublicationRepositoryTest {
    public static String TITLE = "TITLE";
    public static String UPDATED_TITLE = "UPDATED TEXT";
    public static TextOrImage TEXT_OR_IMAGE1 = new TextOrImage();
    public static TextOrImage TEXT_OR_IMAGE2 = new TextOrImage();
    public static List<TextOrImage> TEXT_OR_IMAGE_LIST = new ArrayList<>();
    public static List<Tag> TAGS = new ArrayList<>();
    public static Publication PUBLICATION;

    static {
        TEXT_OR_IMAGE1.setBody("BODY1");
        TEXT_OR_IMAGE1.setIsText(true);
        TEXT_OR_IMAGE2.setBody("BODY2");
        TEXT_OR_IMAGE2.setIsText(true);
        TEXT_OR_IMAGE_LIST.add(TEXT_OR_IMAGE1);
        TEXT_OR_IMAGE_LIST.add(TEXT_OR_IMAGE2);
        TAGS.add(new Tag());
    }

    @Autowired
    private PublicationRepository publicationRepository;

    @Test
    public void createPublication() {
        Publication publication = new Publication();
        publication.setTitle(TITLE);
        publication.setTextOrImages(TEXT_OR_IMAGE_LIST);
        publication.setTags(TAGS);
        Publication createdPublication = publicationRepository.save(publication);
        assertEquals(TITLE, createdPublication.getTitle());
        assertEquals(TEXT_OR_IMAGE_LIST, createdPublication.getTextOrImages());
        assertEquals(TEXT_OR_IMAGE1, createdPublication.getTextOrImages().get(0));
        assertEquals(TEXT_OR_IMAGE2, createdPublication.getTextOrImages().get(1));
        assertEquals(TAGS, createdPublication.getTags());
    }

    @Test
    public void updatePublication() {
        Publication publication = new Publication();
        publication.setTitle(TITLE);
        publication.setTextOrImages(TEXT_OR_IMAGE_LIST);
        publication.setTags(TAGS);
        Publication createdPublication = publicationRepository.save(publication);
        publication = publicationRepository.getOne(createdPublication.getId());
        assertEquals(TITLE, publication.getTitle());
        publication.setTitle(UPDATED_TITLE);
        publication = publicationRepository.save(publication);
        assertEquals(UPDATED_TITLE, publication.getTitle());
    }

    @Test
    public void deletePublication() {
        Publication publication = new Publication();
        publication.setTitle(TITLE);
        publication.setTextOrImages(TEXT_OR_IMAGE_LIST);
        publication.setTags(TAGS);
        Publication createdPublication = publicationRepository.save(publication);
        publication = publicationRepository.getOne(createdPublication.getId());
        assertEquals(TITLE, publication.getTitle());
        publicationRepository.deleteById(publication.getId());
        assertEquals(Optional.empty(), publicationRepository.findById(publication.getId()));
    }
}

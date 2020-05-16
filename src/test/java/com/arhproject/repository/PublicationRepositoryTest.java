package com.arhproject.repository;

import com.arhproject.start.jpa.domain.Publication;
import com.arhproject.start.jpa.repository.PublicationRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.Assert.assertEquals;

@DataJpaTest
public class PublicationRepositoryTest {
    public static String TITLE = "TITLE";
    public static String TEXT = "TEXT";
    public static String UPDATED_TEXT = "UPDATED TEXT";

    @Autowired
    private PublicationRepository publicationRepository;

    @Test
    public void createPublication() {
        Publication publication = new Publication();
        publication.setTitle(TITLE);
        Publication createdPublication = publicationRepository.save(publication);
        assertEquals(TITLE, createdPublication.getTitle());

    }
}

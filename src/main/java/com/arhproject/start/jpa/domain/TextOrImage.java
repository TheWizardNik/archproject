package com.arhproject.start.jpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "text_or_image")
@Getter
@Setter
public class TextOrImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_id", nullable = false)
    private Publication publication;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "is_text", nullable = false)
    private Boolean isText;
}

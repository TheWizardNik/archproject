package com.arhproject.start.jpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publication")
@Getter
@Setter
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "image", nullable = false)
    private String image;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "publication_user",
            joinColumns = {@JoinColumn(name = "publication_id", referencedColumnName = "id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)}
    )
    private List<User> authors = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "publication_tag",
            joinColumns = {@JoinColumn(name = "publication_id", referencedColumnName = "id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "id", nullable = false)}
    )
    private List<Tag> tags = new ArrayList<>();

    @Embedded
    private AuditEmbeddable audit = new AuditEmbeddable();
}

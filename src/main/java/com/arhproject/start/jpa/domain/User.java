package com.arhproject.start.jpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usr")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "photo")
    private String photo;

    @Column(name = "organization")
    private String organization;

    @Column(name = "user_status", nullable = false)
    private UserStatus userStatus;

    @Column(name = "about")
    private String about;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usr_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)}
    )
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notification> notifications = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_followed_user",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)}
    )
    private List<User> followedUsers = new ArrayList<>();

    @ManyToMany(mappedBy = "publications", fetch = FetchType.LAZY)
    private List<Publication> publications = new ArrayList<>();

    @Embedded
    private AuditEmbeddable audit = new AuditEmbeddable();

}

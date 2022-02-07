package com.mywork.discoman.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "masteralbum")
public class MasterAlbum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String images;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private String style;

    @ManyToMany
    @JoinTable(name = "include",
                joinColumns = @JoinColumn(name = "masteralbum_id"),
                inverseJoinColumns = @JoinColumn(name = "music_id"))
    private Set<Music> musics;

    // review
}

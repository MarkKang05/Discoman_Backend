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

    private String description;
    private String images;
    private String genre;
    private String style;

    @ManyToMany
    @JoinTable(name = "include",
                joinColumns = @JoinColumn(name = "masteralbum_id"),
                inverseJoinColumns = @JoinColumn(name = "music_id"))
    private Set<Music> musics;

    // review
}

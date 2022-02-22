package com.mywork.discoman.domain.masterAlbum.domain;

import com.mywork.discoman.domain.artist.domain.Artist;
import com.mywork.discoman.domain.music.domain.Music;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "masteralbum")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    private String images;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private String style;

    @OneToMany
    @JoinColumn(name = "masterAlbum_id")
    private Set<Music> musics = new HashSet<>();

    // review
}

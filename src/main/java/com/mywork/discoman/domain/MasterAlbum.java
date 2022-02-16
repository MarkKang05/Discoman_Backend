package com.mywork.discoman.domain;

import com.mywork.discoman.dto.ResponseMasterAlbumDto;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
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

    @ManyToMany
    @JoinTable(name = "album_music",
                joinColumns = @JoinColumn(name = "masteralbum_id"),
                inverseJoinColumns = @JoinColumn(name = "music_id"))
    private Set<Music> musics = new HashSet<>();

    // review
}

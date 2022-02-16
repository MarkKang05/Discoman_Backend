package com.mywork.discoman.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "music")
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int duration;

    @ManyToMany(mappedBy = "musics")
    private Set<MasterAlbum> masterAlbums = new HashSet<>();
}

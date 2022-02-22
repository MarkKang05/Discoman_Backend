package com.mywork.discoman.domain.music.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mywork.discoman.domain.masterAlbum.domain.MasterAlbum;
import lombok.Builder;
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
@Builder
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String disk;

    private int index;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int duration;

}

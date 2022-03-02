package com.mywork.discoman.domain.music.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mywork.discoman.domain.masterAlbum.domain.MasterAlbum;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "music")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String disk;

    @Column(name = "m_index", nullable = false)
    private int index;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int duration;

}

package com.mywork.discoman.domain;

import javax.persistence.*;

@Entity
@Table(name = "releasealbum")
public class ReleaseAlbum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "masterAlbum_id")
    private MasterAlbum masterAlbum;

    private String format;
    @ManyToOne
    @JoinColumn(name = "label_id")
    private Label label;

    private String catalogue;
    private String country;
    private int released;

    //review
}

package com.mywork.discoman.domain;

import javax.persistence.*;

@Entity
@Table(name = "releasealbum")
public class ReleaseAlbum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "masteralbum_id")
    private MasterAlbum masterAlbum;

    @Column(nullable = false)
    private String format;

    @ManyToOne
    @JoinColumn(name = "label_id")
    private Label label;

    @Column(nullable = false)
    private String catalogue;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private int released;

    //review
}

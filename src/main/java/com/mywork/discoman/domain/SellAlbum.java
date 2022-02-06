package com.mywork.discoman.domain;

import javax.persistence.*;

@Entity
@Table(name = "sellalbum")
public class SellAlbum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Condition condition;

    private int price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User seller;

    @ManyToOne
    @JoinColumn(name = "releaseAlbum_id")
    private ReleaseAlbum releaseAlbum;
}

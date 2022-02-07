package com.mywork.discoman.domain;

import javax.persistence.*;

@Entity
@Table(name = "sellalbum")
public class SellAlbum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

//    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Condition vinylCondition;

    @Column(nullable = false)
    private Condition sleeveCondition;

    @Column(nullable = false)
    private int price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User seller;

    @ManyToOne
    @JoinColumn(name = "releasealbum_id")
    private ReleaseAlbum releaseAlbum;
}

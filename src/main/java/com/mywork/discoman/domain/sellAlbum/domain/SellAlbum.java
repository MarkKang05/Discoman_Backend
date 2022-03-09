package com.mywork.discoman.domain.sellAlbum.domain;

import com.mywork.discoman.domain.releaseAlbum.domain.ReleaseAlbum;
import com.mywork.discoman.domain.sellAlbum.domain.Condition;
import com.mywork.discoman.domain.sellAlbumImage.domain.SellAlbumImage;
import com.mywork.discoman.domain.user.domain.User;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany
    @JoinColumn(name = "sellalbum_id")
    private List<SellAlbumImage> sellAlbumImage;
}

package com.mywork.discoman.domain.releaseAlbum.domain;

import com.mywork.discoman.domain.label.domain.Label;
import com.mywork.discoman.domain.masterAlbum.domain.MasterAlbum;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "releasealbum")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @Column(nullable = false)
    private boolean isAccept=false;

    //review
}

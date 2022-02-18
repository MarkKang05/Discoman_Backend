package com.mywork.discoman.domain.artist.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "artist"
//    uniqueConstraints = {
//        @UniqueConstraint(
//                columnNames = {"name", "image"}
//        )
//    }
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

//    @Column(nullable = false)
    private String image;

    private String website;

    @Column(nullable = false)
    private String members;

//    @ManyToOne
//    @JoinColumn(name = "masteralbum_id")
//    private MasterAlbum masterAlbum;

}

package com.mywork.discoman.domain.user.domain;

import com.mywork.discoman.global.common.domain.BaseTimeEntity;
import com.mywork.discoman.domain.releaseAlbum.domain.ReleaseAlbum;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

//    @Column(name = "user_role", nullable = false)
//    @ManyToMany(cascade=CascadeType.ALL)
//    @JoinTable(name = "UserAuthority",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<EnumRole> roles;

    @ManyToMany
    @JoinTable(name = "want",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "releasealbum_id"))
    private Set<ReleaseAlbum> wantList;

}

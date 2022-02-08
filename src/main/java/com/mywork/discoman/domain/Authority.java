package com.mywork.discoman.domain;

import lombok.Builder;

import javax.persistence.*;

@Entity
@Builder
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private EnumRole role;
}

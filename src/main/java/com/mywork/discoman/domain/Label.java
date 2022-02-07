package com.mywork.discoman.domain;

import javax.persistence.*;

@Entity
@Table(name = "label")
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String site;

    @Column(nullable = false)
    private String contactInfo;
}

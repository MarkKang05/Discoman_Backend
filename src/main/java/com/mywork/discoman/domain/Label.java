package com.mywork.discoman.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "label")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String site;

    @Column(nullable = false)
    private String contactInfo;
}

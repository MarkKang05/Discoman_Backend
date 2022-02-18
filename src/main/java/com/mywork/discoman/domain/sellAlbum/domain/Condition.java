package com.mywork.discoman.domain.sellAlbum.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public enum Condition {
    M("Mint"),
    NM("Near Mint"),
    VGP("Very Good Plus"),
    VG("Very Good"),
    G("Good"),
    P("Poor"),
    F("Fair");

    private String des;
}

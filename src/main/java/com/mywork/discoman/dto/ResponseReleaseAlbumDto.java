package com.mywork.discoman.dto;

import com.mywork.discoman.domain.ReleaseAlbum;
import lombok.Getter;

@Getter
public class ResponseReleaseAlbumDto {

    private Long id;
    private Long masterAlbum;
    private String format;
    private Long label;
    private String catalogue;
    private String country;
    private int released;
    public ResponseReleaseAlbumDto(ReleaseAlbum s, Long masterAlbumId, Long longId) {
        this.id = s.getId();
        this.masterAlbum = masterAlbumId;
        this.format = s.getFormat();
        this.label = longId;
        this.catalogue = s.getCatalogue();
        this.country = s.getCountry();
        this.released = s.getReleased();
    }

    public ResponseReleaseAlbumDto(ReleaseAlbum album) {

        this.id = album.getId();
        this.masterAlbum = album.getMasterAlbum().getId();
        this.format = album.getFormat();
        this.label = album.getLabel().getId();
        this.catalogue = album.getCatalogue();
        this.country = album.getCountry();
        this.released = album.getReleased();

    }
}

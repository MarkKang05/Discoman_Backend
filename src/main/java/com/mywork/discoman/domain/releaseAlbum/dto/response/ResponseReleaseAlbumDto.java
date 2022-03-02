package com.mywork.discoman.domain.releaseAlbum.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mywork.discoman.domain.label.domain.Label;
import com.mywork.discoman.domain.masterAlbum.domain.MasterAlbum;
import com.mywork.discoman.domain.releaseAlbum.domain.ReleaseAlbum;
import lombok.Getter;

@Getter
@JsonPropertyOrder({
        "id",
        "format",
        "catalogue",
        "country",
        "released",
        "masterAlbum",
        "label"
})
public class ResponseReleaseAlbumDto {

    private Long id;
    private MasterAlbum masterAlbum;
    private String format;
    private Label label;
    private String catalogue;
    private String country;
    private int released;
//    public ResponseReleaseAlbumDto(ReleaseAlbum s, Long masterAlbumId, Long longId) {
//        this.id = s.getId();
//        this.masterAlbum = masterAlbumId;
//        this.format = s.getFormat();
//        this.label = longId;
//        this.catalogue = s.getCatalogue();
//        this.country = s.getCountry();
//        this.released = s.getReleased();
//    }

    public ResponseReleaseAlbumDto(ReleaseAlbum album) {
//        album.getMasterAlbum().getMusics().forEach(m-> m.setMasterAlbums(null));

        this.id = album.getId();
        this.masterAlbum = album.getMasterAlbum();
        this.format = album.getFormat();
        this.label = album.getLabel();
        this.catalogue = album.getCatalogue();
        this.country = album.getCountry();
        this.released = album.getReleased();

    }
}

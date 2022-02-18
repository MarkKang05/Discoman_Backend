package com.mywork.discoman.domain.releaseAlbum.dto.request;

import com.mywork.discoman.domain.releaseAlbum.domain.ReleaseAlbum;
import lombok.Getter;

@Getter
public class RequestReleaseAlbumDto {
    private Long masterAlbum;
    private String format;
    private Long label;
    private String catalogue;
    private String country;
    private int released;

    public ReleaseAlbum toEntity(){
        return ReleaseAlbum.builder()
                .format(format)
                .catalogue(catalogue)
                .country(country)
                .released(released)
                .build();
    }
}

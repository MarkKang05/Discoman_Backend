package com.mywork.discoman.dto;

import com.mywork.discoman.domain.MasterAlbum;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMasterAlbumDto {

    private Long id;
    private String title;
    private String description;
    private String images;
    private String genre;
    private String style;
    private Long artist;
    private List<Long> musics;

    public ResponseMasterAlbumDto(MasterAlbum masterAlbum) {
        id = masterAlbum.getId();
        title = masterAlbum.getTitle();
        description = masterAlbum.getDescription();
        images = masterAlbum.getImages();
        genre = masterAlbum.getGenre();
        style = masterAlbum.getStyle();
    }
}

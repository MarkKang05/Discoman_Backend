package com.mywork.discoman.domain.masterAlbum.dto.request;

import com.mywork.discoman.domain.masterAlbum.domain.MasterAlbum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestMasterAlbumsDto {

    private String title;
    private String description;
//    private String images;
    private String genre;
    private String style;
    private Long artist;
    private List<Long> musics;

    public MasterAlbum toEntity(){

        return MasterAlbum.builder()
                .title(title)
                .description(description)
//                .images(images)
                .genre(genre)
                .style(style)
                .build();
    }

}

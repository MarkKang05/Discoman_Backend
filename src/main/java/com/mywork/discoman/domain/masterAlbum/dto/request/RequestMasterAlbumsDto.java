package com.mywork.discoman.domain.masterAlbum.dto.request;

import com.mywork.discoman.domain.masterAlbum.domain.MasterAlbum;
import com.mywork.discoman.domain.music.domain.Music;
import com.mywork.discoman.domain.music.dto.RequestMusicDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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
    private List<RequestMusicDto> musics;

    public MasterAlbum toEntity(){

        return MasterAlbum.builder()
                .title(title)
                .description(description)
//                .images(images)
                .genre(genre)
                .style(style)
                .build();
    }

    public List<Music> toMusicEntity(){
        List<Music> musicList = new ArrayList<>();

        musics.forEach(m-> musicList.add(m.toEntity()));

        return musicList;
    }

}

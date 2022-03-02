package com.mywork.discoman.domain.masterAlbum.dto.request;

import com.mywork.discoman.domain.masterAlbum.domain.MasterAlbum;
import com.mywork.discoman.domain.music.domain.Music;
import com.mywork.discoman.domain.music.dto.RequestMusicDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class RequestMasterAlbumsDto {

    private String title;
    private String description;
//    private String images;
    private String genre;
    private String style;
    private Long artist;
    private Set<RequestMusicDto> musics;

    public MasterAlbum toEntity(){

        return MasterAlbum.builder()
                .title(title)
                .description(description)
//                .images(images)
                .genre(genre)
                .style(style)
                .build();
    }

    public Set<Music> toMusicEntity(){
        Set<Music> musicList = new HashSet<>();

        musics.forEach(m-> musicList.add(m.toEntity()));

        return musicList;
    }

}

package com.mywork.discoman.dto;

import com.mywork.discoman.domain.MasterAlbum;
import com.mywork.discoman.domain.Music;
import com.mywork.discoman.repository.ArtistRepository;
import com.mywork.discoman.repository.MusicRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class CreateMasterAlbumsDto {

    private String title;
    private String description;
    private String images;
    private String genre;
    private String style;
    private Long artist;
    private List<Long> musics;

    public MasterAlbum toEntity(){

        return MasterAlbum.builder()
                .title(title)
                .description(description)
                .images(images)
                .genre(genre)
                .style(style)
                .build();
    }

}

package com.mywork.discoman.domain.masterAlbum.dto.response;

import com.mywork.discoman.domain.artist.domain.Artist;
import com.mywork.discoman.domain.masterAlbum.domain.MasterAlbum;
import com.mywork.discoman.domain.music.domain.Music;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    private Artist artist;
    private List<Music> musics;

    public ResponseMasterAlbumDto(MasterAlbum masterAlbum, Long artistId, List<Long> musics) {
        id = masterAlbum.getId();
        title = masterAlbum.getTitle();
        description = masterAlbum.getDescription();
        images = masterAlbum.getImages();
        genre = masterAlbum.getGenre();
        style = masterAlbum.getStyle();
//        artist = artistId;
//        this.musics = musics;
    }

    public ResponseMasterAlbumDto(MasterAlbum masterAlbum) {
        id = masterAlbum.getId();
        title = masterAlbum.getTitle();
        description = masterAlbum.getDescription();
        images = masterAlbum.getImages();
        genre = masterAlbum.getGenre();
        style = masterAlbum.getStyle();
        artist = masterAlbum.getArtist();

        List<Music> musicList = new ArrayList<>(masterAlbum.getMusics());
        musicList.forEach(m -> m.setMasterAlbums(null));

        musics = musicList;
    }
}

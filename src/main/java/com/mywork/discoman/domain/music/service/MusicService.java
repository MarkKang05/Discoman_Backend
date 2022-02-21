package com.mywork.discoman.domain.music.service;

import com.mywork.discoman.domain.music.dao.MusicRepository;
import com.mywork.discoman.domain.music.domain.Music;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicService {

    private final MusicRepository musicRepository;

    public List<Music> getSearchList(String name){
        List<Music> musicList = musicRepository.findByNameContainingIgnoreCase(name);
        musicList.forEach(music -> {
            music.getMasterAlbums().forEach(masterAlbum -> masterAlbum.setMusics(null));
        });

        return musicList;
    }
}

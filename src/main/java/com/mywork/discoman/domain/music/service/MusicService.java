package com.mywork.discoman.domain.music.service;

import com.mywork.discoman.domain.music.dao.MusicRepository;
import com.mywork.discoman.domain.music.domain.Music;
import com.mywork.discoman.domain.music.dto.RequestMusicDto;
import com.mywork.discoman.domain.music.dto.ResponseMusicDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicService {

    private final MusicRepository musicRepository;

//    public List<Music> getSearchList(String name){
//        List<Music> musicList = musicRepository.findByNameContainingIgnoreCase(name);
//        musicList.forEach(music -> {
//            music.getMasterAlbums().forEach(masterAlbum -> masterAlbum.setMusics(null));
//        });

//        return musicList;
//    }

//    public ResponseMusicDto addMusic(RequestMusicDto musicDto){
//        Music music = musicDto.toEntity();
//
//        musicRepository.save(music)
//    }

    public Music createMusic(RequestMusicDto musicDto){
        return musicRepository.save(musicDto.toEntity());
    }
}

package com.mywork.discoman.domain.music.api;

import com.mywork.discoman.domain.music.domain.Music;
import com.mywork.discoman.domain.music.dto.RequestMusicDto;
import com.mywork.discoman.domain.music.service.MusicService;
import com.mywork.discoman.global.common.response.BasicResponse;
import com.mywork.discoman.global.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/musics")
public class MusicController {

    private final MusicService musicService;

//    @GetMapping("/searchByName")
//    public ResponseEntity<? extends BasicResponse> getSearchedMusic(@RequestParam("name") String name){
//        List<Music> searchList = musicService.getSearchList(name);
//        return ResponseEntity.ok().body(new CommonResponse<List<Music>>(searchList));
//    }

    @PostMapping
    public ResponseEntity<? extends BasicResponse> craeteMusic(@RequestBody RequestMusicDto musicDto){
        Music music = musicService.createMusic(musicDto);
        return ResponseEntity.ok(new CommonResponse<Music>(music));

    }
}

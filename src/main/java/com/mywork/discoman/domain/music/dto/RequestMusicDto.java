package com.mywork.discoman.domain.music.dto;

import com.mywork.discoman.domain.music.domain.Music;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestMusicDto {
    private String disk;
    private int index;
    private String name;
    private int duration;

    public Music toEntity(){
        return Music.builder()
                .disk(disk)
                .index(index)
                .name(name)
                .duration(duration)
                .build();
    }
}

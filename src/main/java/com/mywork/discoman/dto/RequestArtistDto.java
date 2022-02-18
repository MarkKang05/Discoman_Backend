package com.mywork.discoman.dto;

import com.mywork.discoman.domain.Artist;
import lombok.Getter;

@Getter
public class RequestArtistDto {
    private String name;
    private String description;
    private String members;

    public Artist toEntity(){
        return Artist.builder()
                .name(name)
                .description(description)
                .members(members)
                .build();
    }
}

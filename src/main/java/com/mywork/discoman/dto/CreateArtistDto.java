package com.mywork.discoman.dto;

import com.mywork.discoman.domain.Artist;
import lombok.Data;
import lombok.Getter;

@Getter
public class CreateArtistDto {
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

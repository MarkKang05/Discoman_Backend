package com.mywork.discoman.dto;

import com.mywork.discoman.domain.Label;
import lombok.Getter;

@Getter
public class CreateLabelDto {
    private String name;
    private String description;
    private String contactInfo;
    private String site;

    public Label toEntity(){
        return Label.builder()
                .name(name)
                .description(description)
                .contactInfo(contactInfo)
                .site(site)
                .build();
    }
}

package com.mywork.discoman.domain.label.dto.request;

import com.mywork.discoman.domain.label.domain.Label;
import lombok.Getter;

@Getter
public class RequestLabelDto {
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

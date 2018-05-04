package com.depromeet.tifyapi.model;

import com.depromeet.tifyapi.dto.PresentDto;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Present {
    private Integer presentId;
    private String name;
    private String description;
    private String link;
    private String image;

    public PresentDto toPresentDto() {
        return PresentDto.builder()
                .presentId(presentId)
                .name(name)
                .description(description)
                .link(link)
                .link(image)
                .build();
    }
}

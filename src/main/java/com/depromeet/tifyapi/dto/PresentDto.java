package com.depromeet.tifyapi.dto;

import com.depromeet.tifyapi.model.Present;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PresentDto {
    private Integer presentId;
    private String name;
    private String description;
    private String link;

    public Present toPresent() {
        return Present.builder()
                .presentId(presentId)
                .name(name)
                .description(description)
                .link(link)
                .build();
    }
}

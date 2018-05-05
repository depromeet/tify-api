package com.depromeet.tifyapi.dto;

import com.depromeet.tifyapi.model.Present;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
public class PresentDto {
    private Integer presentId;
    private String name;
    private String description;
    private String link;
    @JsonIgnore
    private MultipartFile image;
    @JsonProperty("image")
    private String imageLink;

    public Present toPresent() {
        return Present.builder()
                .presentId(presentId)
                .name(name)
                .description(description)
                .link(link)
                .build();
    }

    public Present toPresent(String imageLink) {
        return Present.builder()
                .presentId(presentId)
                .name(name)
                .description(description)
                .link(link)
                .image(imageLink)
                .build();
    }

    public static PresentDto of(Present present) {
        return PresentDto.builder()
                .presentId(present.getPresentId())
                .name(present.getName())
                .description(present.getDescription())
                .link(present.getLink())
                .imageLink(present.getImage())
                .build();
    }

}

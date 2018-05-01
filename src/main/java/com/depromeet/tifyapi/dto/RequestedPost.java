package com.depromeet.tifyapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestedPost {
    private String link;
    private Integer receiverId;

    public PostDto toPostDto() {
        return PostDto.builder()
                .link(link)
                .receiverId(receiverId)
                .build();
    }
}

package com.depromeet.tifyapi.model;

import com.depromeet.tifyapi.dto.PostDto;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Post {
    private Integer postId;
    private String link;
    private Integer receiverId;

    public PostDto toPostDto() {
        return PostDto.builder()
                .postId(postId)
                .link(link)
                .receiverId(receiverId)
                .build();
    }
}

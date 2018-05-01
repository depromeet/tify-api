package com.depromeet.tifyapi.dto;

import com.depromeet.tifyapi.model.Post;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostDto {
    private Integer postId;
    private String link;
    private Integer receiverId;

    public Post toPost() {
        return Post.builder()
                .postId(postId)
                .link(link)
                .receiverId(receiverId)
                .build();
    }
}

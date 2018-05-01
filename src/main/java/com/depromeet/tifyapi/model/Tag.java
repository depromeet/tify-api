package com.depromeet.tifyapi.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Tag {
    private Integer tagId;
    private String name;
    private Integer postId;
}

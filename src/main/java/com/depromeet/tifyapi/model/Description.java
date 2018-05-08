package com.depromeet.tifyapi.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Description {
    private Integer descriptionId;
    private Integer postId;
    private Integer tagId;
}

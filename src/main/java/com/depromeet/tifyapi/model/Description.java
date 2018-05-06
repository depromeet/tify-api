package com.depromeet.tifyapi.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Description {
    Integer descriptionId;
    Integer postId;
    Integer tagId;
}

package com.depromeet.tifyapi.model;

import com.depromeet.tifyapi.dto.TagDto;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Tag {
    private Integer tagId;
    private String name;
}

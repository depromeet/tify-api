package com.depromeet.tifyapi.dto;

import com.depromeet.tifyapi.model.Tag;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TagDto {
    private Integer tagId;
    private String name;

    public Tag toTag() {
        return Tag.builder()
                .tagId(tagId)
                .name(name)
                .build();
    }

    public static TagDto of(Tag tag) {
        return TagDto.builder()
                .tagId(tag.getTagId())
                .name(tag.getName())
                .build();
    }
}

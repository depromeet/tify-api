package com.depromeet.tifyapi.service;

import com.depromeet.tifyapi.dto.PostDto;
import com.depromeet.tifyapi.dto.TagDto;

import java.util.List;
import java.util.Optional;

public interface TagService {
    Optional<TagDto> getTag(Integer tagId);
    List<TagDto> getTags();
    List<TagDto> getTagsByPostId(Integer postId);
    Integer createTag(TagDto tagDto);
    Integer createRecommendation(Integer tagId, Integer postId);
}

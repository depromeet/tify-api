package com.depromeet.tifyapi.service;

import com.depromeet.tifyapi.Exception.NoContentException;
import com.depromeet.tifyapi.dto.TagDto;
import com.depromeet.tifyapi.mapper.TagMapper;
import com.depromeet.tifyapi.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public Optional<TagDto> getTag(Integer tagId) {
        return Optional.empty();
    }

    @Override
    public List<TagDto> getTags() {
        return Optional.ofNullable(tagMapper.findAll())
                .orElse(Collections.emptyList())
                .stream()
                .map(TagDto::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<TagDto> getTagsByPostId(Integer postId) {
        return Optional.ofNullable(tagMapper.findTagsByPostId(postId))
                .orElse(Collections.emptyList())
                .stream()
                .map(TagDto::of)
                .collect(Collectors.toList());
    }

    @Override
    public Integer createTag(TagDto tagDto) {
        Tag tag = tagDto.toTag();
        if (tagMapper.createTag(tag) != 1) {
            throw new NoContentException();
        }
        return tag.getTagId();
    }

    @Override
    public Optional<TagDto> getTagByName(String name) {
        return Optional.ofNullable(tagMapper.findTagByName(name))
                .map(TagDto::of);
    }

    @Override
    public List<TagDto> getTagsIncludingName(String name) {
        return Optional.ofNullable(tagMapper.findTagsIncludingName(name))
                .orElse(Collections.emptyList())
                .stream()
                .map(TagDto::of)
                .collect(Collectors.toList());
    }
}

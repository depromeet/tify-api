package com.depromeet.tifyapi.controller;

import com.depromeet.tifyapi.Exception.NoContentException;
import com.depromeet.tifyapi.dto.TagDto;
import com.depromeet.tifyapi.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping
    public List<TagDto> getTags() {
        return tagService.getTags();
    }

    @GetMapping("/{tagId:\\d+}")
    public TagDto getTag(@PathVariable("tagId") Integer tagId) {
        return tagService.getTag(tagId)
                .orElseThrow(() -> new NoContentException());
    }

    @PostMapping
    public Integer addTag(@RequestBody TagDto tagDto) {
        return tagService.createTag(tagDto);
    }
}

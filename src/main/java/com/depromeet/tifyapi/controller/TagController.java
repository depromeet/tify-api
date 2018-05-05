package com.depromeet.tifyapi.controller;

import com.depromeet.tifyapi.Exception.NoContentException;
import com.depromeet.tifyapi.dto.TagDto;
import com.depromeet.tifyapi.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping
    public List<TagDto> getTags(@RequestParam(value = "name", required = false, defaultValue = "") String name,
                                @RequestParam(value = "type", required = false, defaultValue = "equal") String type) {
        if (StringUtils.isEmpty(name)) {
            return tagService.getTags();
        }

        if ("equal".equals(type)) {
            return tagService.getTagByName(name)
                    .map(Collections::singletonList)
                    .orElse(Collections.emptyList());
        } else if ("include".equals(type)) {
            return tagService.getTagsIncludingName(name);
        } else {
            throw new NoContentException();
        }
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

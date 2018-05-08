package com.depromeet.tifyapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.depromeet.tifyapi.Exception.NoContentException;
import com.depromeet.tifyapi.dto.TagDto;
import com.depromeet.tifyapi.service.TagService;
import org.springframework.util.StringUtils;

import java.util.Collections;

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
    
    @PostMapping("/{tagId}/presents")
    public Integer addpresent(@PathVariable("tagId") Integer tagId, @RequestParam("presentId") Integer presentId) {
    	return tagService.createRecommendation(tagId, presentId);//return recommend_id 
    }
    
}

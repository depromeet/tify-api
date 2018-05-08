package com.depromeet.tifyapi.controller;

import com.depromeet.tifyapi.Exception.NoContentException;
import com.depromeet.tifyapi.dto.PresentDto;
import com.depromeet.tifyapi.service.PresentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/presents")
public class PresentController {
    @Autowired
    private PresentService presentService;

    @GetMapping
    public List<PresentDto> getPresents() {
        return presentService.getPresents();
    }

    @GetMapping("/{presentId:\\d+}")
    public PresentDto getPresent(@PathVariable("presentId") Integer presentId) {
        return presentService.getPresent(presentId)
                .orElseThrow(() -> new NoContentException());
    }

    @PostMapping
    public int addPresent(@RequestParam("name") String name,
                          @RequestParam("description") String description,
                          @RequestParam("link") String link,
                          @RequestParam("image") MultipartFile imageFile) {
        return presentService.createPresent(PresentDto.builder()
                .name(name)
                .description(description)
                .link(link)
                .image(imageFile)
                .build());
    }
    
    @PostMapping("/{presentId}/tags")
    public Integer addpresent(@PathVariable("presentId") Integer presentId, @RequestParam("tagId") Integer tagId) {
    	return presentService.createRecommendation(tagId, presentId);//return recommend_id 
    }
}

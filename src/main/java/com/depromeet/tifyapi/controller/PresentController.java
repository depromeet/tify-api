package com.depromeet.tifyapi.controller;

import com.depromeet.tifyapi.Exception.NoContentException;
import com.depromeet.tifyapi.dto.PresentDto;
import com.depromeet.tifyapi.service.PresentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public int addPresent(@RequestBody PresentDto presentDto) {
        return presentService.createPresent(presentDto);
    }

}

package com.depromeet.tifyapi.controller;

import com.depromeet.tifyapi.Exception.NoContentException;
import com.depromeet.tifyapi.dto.PostDto;
import com.depromeet.tifyapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public List<PostDto> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{postId:\\d+}")
    public PostDto getPost(@PathVariable(value = "postId") Integer postId){
        return postService.getPost(postId)
                .orElseThrow(() -> new NoContentException());
    }

    @PostMapping
    public int addPost(@RequestBody PostDto postDto) {
        return postService.createPost(postDto);
    }
}

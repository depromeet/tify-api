package com.depromeet.tifyapi.controller;

import com.depromeet.tifyapi.Exception.NoContentException;
import com.depromeet.tifyapi.dto.PostDto;
import com.depromeet.tifyapi.dto.RequestedPost;
import com.depromeet.tifyapi.dto.TagDto;
import com.depromeet.tifyapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
    public int addPost(@RequestParam("image") MultipartFile multipartFile,
                       @RequestParam("name") String name,
                       @RequestParam("anniversary") String anniversary,
                       @RequestParam("tags") String tags) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return postService.createPost(RequestedPost.builder()
                .name(name)
                .tags(Arrays.asList(tags.split(",")))
                .anniversary(formatter.parse(anniversary))
                .image(multipartFile)
                .build());
    }

    @PostMapping("/{postId:\\d+}/tags")
    public ResponseEntity<String> addTagToPost(@PathVariable("postId") Integer postId,
                                               @RequestParam String tagName) {
        postService.addTagToPost(postId, tagName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{postId:\\d+}/tags")
    public List<TagDto> getTagsInPost(@PathVariable("postId") Integer postId) {
        return postService.getTagsInPost(postId);
    }
}

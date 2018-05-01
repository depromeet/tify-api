package com.depromeet.tifyapi.service;

import com.depromeet.tifyapi.dto.PostDto;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<PostDto> getPosts();
    Optional<PostDto> getPost(Integer postId);
    Integer addPost(PostDto postDto);
}

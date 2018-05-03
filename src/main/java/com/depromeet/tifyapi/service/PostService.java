package com.depromeet.tifyapi.service;

import com.depromeet.tifyapi.dto.PostDto;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Optional<PostDto> getPost(Integer postId);
    List<PostDto> getPosts();
    Integer createPost(PostDto postDto);
}

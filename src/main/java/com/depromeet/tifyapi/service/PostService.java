package com.depromeet.tifyapi.service;

import com.depromeet.tifyapi.dto.PostDto;
import com.depromeet.tifyapi.dto.RequestedPost;
import com.depromeet.tifyapi.dto.TagDto;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Optional<PostDto> getPost(Integer postId);
    List<PostDto> getPosts();
    Integer createPost(PostDto postDto);
    Integer addTagToPost(Integer postId, String tagName);
    List<TagDto> getTagsInPost(Integer postId);
    Integer createPost(RequestedPost requestedPost);
}

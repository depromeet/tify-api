package com.depromeet.tifyapi.service;

import com.depromeet.tifyapi.dto.PostDto;
import com.depromeet.tifyapi.mapper.PostMapper;
import com.depromeet.tifyapi.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;

    @Override
    public List<PostDto> getPosts() {
        List<Post> posts = postMapper.findAll();
        if (posts == null) {
            return Collections.emptyList();
        }
        return posts.stream().map(Post::toPostDto).collect(Collectors.toList());
    }

    @Override
    public Optional<PostDto> getPost(Integer postId) {
        return Optional.ofNullable(postMapper.findOne(postId))
                .map(Post::toPostDto);
    }

    @Override
    public Integer addPost(PostDto postDto) {
        return postMapper.createPost(postDto.toPost());
    }
}
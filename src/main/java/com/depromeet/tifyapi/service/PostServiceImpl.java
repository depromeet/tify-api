package com.depromeet.tifyapi.service;

import com.depromeet.tifyapi.Exception.NoContentException;
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
    public Optional<PostDto> getPost(Integer postId) {
        return Optional.ofNullable(postMapper.findOne(postId))
                .map(Post::toPostDto);
    }

    @Override
    public List<PostDto> getPosts() {
        return Optional.ofNullable(postMapper.findAll())
                .orElse(Collections.emptyList())
                .stream()
                .map(Post::toPostDto)
                .collect(Collectors.toList());
    }

    @Override
    public Integer createPost(PostDto postDto) {
        Post post = postDto.toPost();
        if (postMapper.createPost(post) != 1) {
            throw new NoContentException();
        }
        return post.getPostId();
    }

//    @Override
//    public ResponsePost getResponsePost(Integer postId) {
//        // post
//        PostDto post = getPost(postId).orElseThrow(() -> new NoContentException());
//        // tag
//        List<TagDto> tags = tagService.getTagsByPostId(postId);
//        // receiver
//        ReceiverDto receiver = receiverService.getReceiver(post.getReceiverId());
//
//        return ResponsePost.builder()
//                .postId(post.getPostId())
//                .link(post.getLink())
//                .receiver(receiver)
//                .tags(tags)
//                .build();
//    }
}
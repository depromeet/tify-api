package com.depromeet.tifyapi.service;

import com.depromeet.tifyapi.Exception.BadRequestException;
import com.depromeet.tifyapi.Exception.NoContentException;
import com.depromeet.tifyapi.dto.PostDto;
import com.depromeet.tifyapi.dto.TagDto;
import com.depromeet.tifyapi.mapper.DescriptionMapper;
import com.depromeet.tifyapi.mapper.PostMapper;
import com.depromeet.tifyapi.mapper.TagMapper;
import com.depromeet.tifyapi.model.Description;
import com.depromeet.tifyapi.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
@Slf4j
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private DescriptionMapper descriptionMapper;

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

    @Override
    @Transactional
    public Integer addTagToPost(Integer postId, String tagName) {
        if (StringUtils.isEmpty(tagName)) {
            throw new BadRequestException();
        }

        if (postMapper.findOne(postId) == null) {
            throw new BadRequestException();
        }

        TagDto tagDto = Optional.ofNullable(tagMapper.findTagByName(tagName))
                .map(TagDto::of)
                .orElseThrow(() -> new BadRequestException());

        Description description = Description.builder()
                .postId(postId)
                .tagId(tagDto.getTagId())
                .build();

        try {
            descriptionMapper.createDescription(description);
        } catch (DuplicateKeyException e) {
            log.trace("DuplicateKey : postId={}, tagId={}", description.getPostId(), description.getTagId());
        } finally {
            return 1;
        }
    }

    @Override
    public List<TagDto> getTagsInPost(Integer postId) {
        return Optional.ofNullable(tagMapper.findTagsByPostId(postId))
                .orElse(Collections.emptyList())
                .stream()
                .map(TagDto::of)
                .collect(Collectors.toList());
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
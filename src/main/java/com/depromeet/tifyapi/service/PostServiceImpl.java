package com.depromeet.tifyapi.service;

import com.depromeet.tifyapi.Exception.ApiFailedException;
import com.depromeet.tifyapi.Exception.BadRequestException;
import com.depromeet.tifyapi.Exception.NoContentException;
import com.depromeet.tifyapi.dto.PostDto;
import com.depromeet.tifyapi.dto.ReceiverDto;
import com.depromeet.tifyapi.dto.RequestedPost;
import com.depromeet.tifyapi.dto.TagDto;
import com.depromeet.tifyapi.mapper.DescriptionMapper;
import com.depromeet.tifyapi.mapper.PostMapper;
import com.depromeet.tifyapi.mapper.TagMapper;
import com.depromeet.tifyapi.model.Description;
import com.depromeet.tifyapi.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
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
    @Qualifier("AwsS3Service")
    @Autowired
    private StorageService storageService;
    @Autowired
    private ReceiverService receiverService;
    @Autowired
    private TagService tagservice;

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

    @Override
    @Transactional
    public Integer createPost(@RequestBody RequestedPost requestedPost) {
        // create receiver
        Integer receiverId = receiverService.createReceiver(ReceiverDto.builder()
                .name(requestedPost.getName())
                .anniversary(requestedPost.getAnniversary())
                .image(requestedPost.getImage())
                .build());
        // create post
        Integer postId = createPost(PostDto.builder()
                .link("")
                .receiverId(receiverId)
                .build());
        // create tags and connect tags to post
        requestedPost.getTags().stream()
                .map(tagName -> tagservice.getOrCreateByTagName(tagName))
                .forEach(tagId -> {
                    try {
                        if (postId != null && tagId != null) {
                            descriptionMapper.createDescription(Description.builder()
                                    .postId(postId)
                                    .tagId(tagId)
                                    .build());
                        }
                    } catch (DuplicateKeyException e) {
                        log.error("DuplicateKeyException");
                    }
                });
        // return post id
        return postId;
    }
}
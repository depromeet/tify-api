package com.depromeet.tifyapi.mapper;

import com.depromeet.tifyapi.model.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    List<Post> findAll();
    Post findOne(@Param("postId") Integer postId);
    Integer createPost(@Param("post") Post post);
}

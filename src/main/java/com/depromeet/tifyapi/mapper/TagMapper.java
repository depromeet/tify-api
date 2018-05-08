package com.depromeet.tifyapi.mapper;

import com.depromeet.tifyapi.model.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagMapper {
    List<Tag> findAll();
    Tag findOne(@Param("tagId") Integer tagId);
    Integer createTag(@Param("tag") Tag tag);
    List<Tag> findTagsByPostId(@Param("postId") Integer postId);
    Tag findTagByName(@Param("name") String name);
    List<Tag> findTagsIncludingName(@Param("name") String name);
}

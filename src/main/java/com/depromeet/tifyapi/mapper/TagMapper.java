package com.depromeet.tifyapi.mapper;

import com.depromeet.tifyapi.model.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagMapper {
    List<Tag> findAll();
    Tag findOne(Integer id);
    Integer addPost(Tag post);
}

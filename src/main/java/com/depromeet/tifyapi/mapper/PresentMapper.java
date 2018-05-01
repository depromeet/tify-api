package com.depromeet.tifyapi.mapper;

import com.depromeet.tifyapi.model.Post;
import com.depromeet.tifyapi.model.Present;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PresentMapper {
    List<Present> findAll();
    Present findOne(Integer id);
    Integer addPost(Present post);
}

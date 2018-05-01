package com.depromeet.tifyapi.mapper;

import com.depromeet.tifyapi.model.Receiver;
import com.depromeet.tifyapi.model.Recommendation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RecommendationMapper {
    List<Recommendation> findAll();
    Recommendation findOne(Integer id);
    Integer addPost(Recommendation post);
}

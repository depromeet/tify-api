package com.depromeet.tifyapi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.depromeet.tifyapi.model.Recommendation;

@Mapper
public interface RecommendationMapper {
    List<Recommendation> findAll();
    Recommendation findOne(@Param("recommendationId") Integer recommendationId);
    Integer createRecommendation(@Param("recommendation") Recommendation recommendation);
}

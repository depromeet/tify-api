package com.depromeet.tifyapi.mapper;

import com.depromeet.tifyapi.model.Recommendation;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RecommendationMapper {
    List<Recommendation> findAll();
    Recommendation findOne(@Param("recommendationId") Integer recommendationId);
    Integer createRecommendation(@Param("recommendation") Recommendation recommendation);
}

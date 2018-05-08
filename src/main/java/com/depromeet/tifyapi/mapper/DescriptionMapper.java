package com.depromeet.tifyapi.mapper;

import com.depromeet.tifyapi.model.Description;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DescriptionMapper {
    Integer createDescription(@Param("description") Description description);
}

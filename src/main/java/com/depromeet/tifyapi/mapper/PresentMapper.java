package com.depromeet.tifyapi.mapper;

import com.depromeet.tifyapi.model.Present;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PresentMapper {
    List<Present> findAll();
    Present findOne(@Param("presentId") Integer presentId);
    Integer createPresent(@Param("present") Present present);
}

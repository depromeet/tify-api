package com.depromeet.tifyapi.mapper;

import com.depromeet.tifyapi.model.Present;
import com.depromeet.tifyapi.model.Receiver;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReceiverMapper {
    List<Receiver> findAll();
    Receiver findOne(Integer id);
    Integer addPost(Receiver post);
}

package com.depromeet.tifyapi.mapper;

import com.depromeet.tifyapi.model.Receiver;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReceiverMapper {
    List<Receiver> findAll();
    Receiver findOne(@Param("receiverId") Integer id);
    Integer createReceiver(@Param("receiver") Receiver receiver);
}

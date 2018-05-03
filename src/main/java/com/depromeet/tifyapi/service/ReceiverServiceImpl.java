package com.depromeet.tifyapi.service;

import com.depromeet.tifyapi.Exception.NoContentException;
import com.depromeet.tifyapi.dto.ReceiverDto;
import com.depromeet.tifyapi.mapper.ReceiverMapper;
import com.depromeet.tifyapi.model.Receiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReceiverServiceImpl implements ReceiverService {
    @Autowired
    ReceiverMapper receiverMapper;

    @Override
    public Optional<ReceiverDto> getReceiver(Integer receiverId) {
        return Optional.ofNullable(receiverMapper.findOne(receiverId))
                .map(Receiver::toReceiverDto);
    }

    @Override
    public List<ReceiverDto> getReceivers() {
        return Optional.ofNullable(receiverMapper.findAll())
                .orElse(Collections.emptyList())
                .stream()
                .map(Receiver::toReceiverDto)
                .collect(Collectors.toList());
    }

    @Override
    public Integer createReceiver(ReceiverDto receiverDto) {
        Receiver receiver = receiverDto.toReceiver();
        if (receiverMapper.createReceiver(receiver) != 1) {
            throw new NoContentException();
        }
        return receiver.getReceiverId();
    }
}

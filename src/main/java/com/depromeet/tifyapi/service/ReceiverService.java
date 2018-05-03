package com.depromeet.tifyapi.service;

import com.depromeet.tifyapi.dto.ReceiverDto;
import com.depromeet.tifyapi.model.Receiver;

import java.util.List;
import java.util.Optional;

public interface ReceiverService {
    Optional<ReceiverDto> getReceiver(Integer receiverId);
    List<ReceiverDto> getReceivers();
    Integer createReceiver(ReceiverDto receiverDto);
}

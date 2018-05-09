package com.depromeet.tifyapi.service;

import com.depromeet.tifyapi.Exception.ApiFailedException;
import com.depromeet.tifyapi.Exception.NoContentException;
import com.depromeet.tifyapi.dto.ReceiverDto;
import com.depromeet.tifyapi.mapper.ReceiverMapper;
import com.depromeet.tifyapi.model.Receiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReceiverServiceImpl implements ReceiverService {
    @Autowired
    ReceiverMapper receiverMapper;
    @Qualifier("AwsS3Service")
    @Autowired
    StorageService storageService;

    @Override
    public Optional<ReceiverDto> getReceiver(Integer receiverId) {
        return Optional.ofNullable(receiverMapper.findOne(receiverId))
                .map(ReceiverDto::of);
    }

    @Override
    public List<ReceiverDto> getReceivers() {
        return Optional.ofNullable(receiverMapper.findAll())
                .orElse(Collections.emptyList())
                .stream()
                .map(ReceiverDto::of)
                .collect(Collectors.toList());
    }

    @Override
    public Integer createReceiver(ReceiverDto receiverDto) {
        String imageLink;

        try {
            imageLink = storageService.uploadObject(receiverDto.getImage().getInputStream(),
                    receiverDto.getImage().getOriginalFilename());
        } catch (IOException e) {
            throw new ApiFailedException("Failed to upload file");
        }

        Receiver receiver = Receiver.builder()
                .name(receiverDto.getName())
                .anniversary(receiverDto.getAnniversary())
                .image(imageLink)
                .build();

        if (receiverMapper.createReceiver(receiver) != 1) {
            throw new NoContentException();
        }
        return receiver.getReceiverId();
    }
}

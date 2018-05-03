package com.depromeet.tifyapi.dto;

import com.depromeet.tifyapi.model.Receiver;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class ReceiverDto {
    private Integer receiverId;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date anniversary;

    public Receiver toReceiver() {
        return Receiver.builder()
                .receiverId(receiverId)
                .name(name)
                .anniversary(anniversary)
                .build();
    }
}

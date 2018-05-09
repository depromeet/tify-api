package com.depromeet.tifyapi.dto;

import com.depromeet.tifyapi.model.Receiver;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Builder
@Getter
public class ReceiverDto {
    private Integer receiverId;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date anniversary;
    @JsonIgnore
    private MultipartFile image;
    @JsonAlias("image")
    private String imageLink;

    public Receiver toReceiver() {
        return Receiver.builder()
                .receiverId(receiverId)
                .name(name)
                .anniversary(anniversary)
                .image(imageLink)
                .build();
    }

    public static ReceiverDto of(Receiver receiver) {
        return ReceiverDto.builder()
                .receiverId(receiver.getReceiverId())
                .name(receiver.getName())
                .anniversary(receiver.getAnniversary())
                .imageLink(receiver.getImage())
                .build();
    }
}

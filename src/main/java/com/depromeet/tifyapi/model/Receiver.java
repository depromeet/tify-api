package com.depromeet.tifyapi.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Receiver {
    private Integer receiverId;
    private String name;
    private LocalDateTime anniversary;
}

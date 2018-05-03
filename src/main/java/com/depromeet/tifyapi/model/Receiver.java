package com.depromeet.tifyapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Receiver {
    private Integer receiverId;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date anniversary;
}

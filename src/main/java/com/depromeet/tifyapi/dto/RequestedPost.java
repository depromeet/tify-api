package com.depromeet.tifyapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Builder
@Getter
public class RequestedPost {
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date anniversary;
    private List<String> tags;
    private MultipartFile image;
}

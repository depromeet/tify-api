package com.depromeet.tifyapi.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Present {
    private Integer presentId;
    private String name;
    private String description;
    private String link;
}

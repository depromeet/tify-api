package com.depromeet.tifyapi.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Recommendation {
    private Integer recommendationId;
    private Integer tagId;
    private Integer presentId;
}

package com.depromeet.tifyapi.service;

import com.depromeet.tifyapi.dto.PresentDto;

import java.util.List;
import java.util.Optional;

public interface PresentService {
    Optional<PresentDto> getPresent(Integer presentId);
    List<PresentDto> getPresents();
    List<PresentDto> getPresentsByTagId(Integer tagId);
    Integer createPresent(PresentDto presentDto);
}

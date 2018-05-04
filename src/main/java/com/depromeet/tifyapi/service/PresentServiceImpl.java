package com.depromeet.tifyapi.service;

import com.depromeet.tifyapi.Exception.NoContentException;
import com.depromeet.tifyapi.dto.PresentDto;
import com.depromeet.tifyapi.mapper.PresentMapper;
import com.depromeet.tifyapi.model.Present;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PresentServiceImpl implements PresentService {
    @Autowired
    private PresentMapper presentMapper;

    @Override
    public Optional<PresentDto> getPresent(Integer presentId) {
        return Optional.ofNullable(presentMapper.findOne(presentId))
                .map(Present::toPresentDto);
    }

    @Override
    public List<PresentDto> getPresents() {
        return Optional.ofNullable(presentMapper.findAll())
                .orElse(Collections.emptyList())
                .stream()
                .map(Present::toPresentDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PresentDto> getPresentsByTagId(Integer tagId) {
        return Optional.ofNullable(presentMapper.findPresentsByTagId(tagId))
                .orElse(Collections.emptyList());
    }

    @Override
    public Integer createPresent(PresentDto presentDto) {
        Present present = presentDto.toPresent();
        if (presentMapper.createPresent(present) != 1) {
            throw new NoContentException();
        }
        return present.getPresentId();
    }
}

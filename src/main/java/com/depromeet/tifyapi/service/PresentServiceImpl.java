package com.depromeet.tifyapi.service;

import com.depromeet.tifyapi.Exception.NoContentException;
import com.depromeet.tifyapi.dto.PresentDto;
import com.depromeet.tifyapi.mapper.PresentMapper;
import com.depromeet.tifyapi.model.Present;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PresentServiceImpl implements PresentService {
    @Autowired
    private PresentMapper presentMapper;
    @Qualifier("AwsS3Service")
    @Autowired
    private StorageService storageService;

    @Override
    public Optional<PresentDto> getPresent(Integer presentId) {
        return Optional.ofNullable(presentMapper.findOne(presentId))
                .map(PresentDto::from);
    }

    @Override
    public List<PresentDto> getPresents() {
        return Optional.ofNullable(presentMapper.findAll())
                .orElse(Collections.emptyList())
                .stream()
                .map(PresentDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<PresentDto> getPresentsByTagId(Integer tagId) {
        return Optional.ofNullable(presentMapper.findPresentsByTagId(tagId))
                .orElse(Collections.emptyList());
    }

    @Override
    public Integer createPresent(PresentDto presentDto) {
        MultipartFile imageFile = presentDto.getImage();
        Present present = presentDto.toPresent(getImageLink(imageFile));
        if (presentMapper.createPresent(present) != 1) {
            throw new NoContentException();
        }
        return present.getPresentId();
    }

    private String getImageLink(MultipartFile imageFile) {
        try {
            return storageService.uploadObject(imageFile.getInputStream(), imageFile.getOriginalFilename());
        } catch (IOException e) {
            log.error("inputStream error : {}", e);
            throw new NoContentException();
        }
    }
}

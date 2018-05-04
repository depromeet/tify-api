package com.depromeet.tifyapi.controller;

import com.depromeet.tifyapi.Exception.NoContentException;
import com.depromeet.tifyapi.dto.ReceiverDto;
import com.depromeet.tifyapi.model.Receiver;
import com.depromeet.tifyapi.service.ReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receivers")
public class ReceiverController {
    @Autowired
    private ReceiverService receiverService;

    @GetMapping
    public List<ReceiverDto> getReceivers() {
        return receiverService.getReceivers();
    }

    @GetMapping("/{receiverId:\\d+}")
    public ReceiverDto getReceiver(@PathVariable("receiverId") Integer receiverId) {
        return receiverService.getReceiver(receiverId)
                .orElseThrow(() -> new NoContentException());
    }

    @PostMapping
    public Integer createReceiver(@RequestBody ReceiverDto receiverDto) {
        return receiverService.createReceiver(receiverDto);
    }
}

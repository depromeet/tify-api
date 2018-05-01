package com.depromeet.tifyapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/l7check")
public class MaintenanceController {
    @GetMapping
    public String checkHealth() {
        return "OK";
    }
}

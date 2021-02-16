package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.AuthenticationServiceRequestDto;
import com.dev.cinema.security.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public void register(@RequestBody AuthenticationServiceRequestDto dto) {
        service.register(dto.getLogin(), dto.getPassword());
    }
}

package com.dev.theatre.controllers;

import com.dev.theatre.model.dto.UserRegistrationDto;
import com.dev.theatre.security.AuthenticationService;
import com.dev.theatre.service.mapper.UserMapper;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService service;
    private final UserMapper userMapper;

    public AuthenticationController(AuthenticationService service, UserMapper userMapper) {
        this.service = service;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid UserRegistrationDto dto) {
        service.register(userMapper.mapUserRegistrationDtoToUser(dto));
    }
}

package com.dev.cinema.controllers;

import com.dev.cinema.security.AuthenticationServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationServiceImpl service;

    public AuthenticationController(AuthenticationServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/register")
    public void register(@RequestBody String login, String password) {
        service.register(login, password);
    }
}

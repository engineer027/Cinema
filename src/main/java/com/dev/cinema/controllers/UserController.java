package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.UserResponseDto;
import com.dev.cinema.service.impl.UserServiceImpl;
import com.dev.cinema.service.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    public UserController(UserServiceImpl userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/by-email")
    public UserResponseDto getByEmail(@RequestParam String mail) {
        return userMapper.mapUserToResponseDto(userService.findByLogin(mail).get());
    }
}

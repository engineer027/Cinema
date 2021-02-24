package com.dev.theatre.service.mapper;

import com.dev.theatre.model.User;
import com.dev.theatre.model.dto.UserRegistrationDto;
import com.dev.theatre.model.dto.UserResponseDto;
import com.dev.theatre.service.RoleService;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final RoleService roleService;

    public UserMapper(RoleService roleService) {
        this.roleService = roleService;
    }

    public UserResponseDto mapUserToResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setLogin(user.getLogin());
        return userResponseDto;
    }

    public User mapUserRegistrationDtoToUser(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setLogin(userRegistrationDto.getLogin());
        user.setPassword(userRegistrationDto.getPassword());
        return user;
    }
}

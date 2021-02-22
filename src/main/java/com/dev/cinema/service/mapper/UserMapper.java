package com.dev.cinema.service.mapper;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.UserRegistrationDto;
import com.dev.cinema.model.dto.UserResponseDto;
import com.dev.cinema.service.RoleService;
import java.util.Set;
import java.util.stream.Collectors;
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
        Set<Role> roles = userRegistrationDto.getRoles().stream()
                .map(p -> roleService.getRoleByName(p))
                .collect(Collectors.toSet());
        user.setRoles(roles);
        return user;
    }
}

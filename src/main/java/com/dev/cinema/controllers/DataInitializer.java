package com.dev.cinema.controllers;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final RoleService roleService;
    private final UserService userService;

    public DataInitializer(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void addRole() {
        Role user = new Role();
        user.setRole("USER");
        roleService.add(user);
        Role admin = new Role();
        admin.setRole("ADMIN");
        roleService.add(admin);
        User bob = new User();
        bob.setPassword("1234");
        bob.setLogin("bob@gmail.com");
        bob.setRoles(Set.of(admin, user));
        userService.add(bob);
    }
}

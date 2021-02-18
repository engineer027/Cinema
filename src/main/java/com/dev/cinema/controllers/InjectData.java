package com.dev.cinema.controllers;

import com.dev.cinema.model.Role;
import com.dev.cinema.service.RoleService;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Controller;

@Controller
public class InjectData {
    private final RoleService roleService;

    public InjectData(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostConstruct
    public void addRole() {
        Role user = new Role();
        user.setRole("USER");
        roleService.add(user);
        Role admin = new Role();
        admin.setRole("ADMIN");
        roleService.add(admin);
    }
}

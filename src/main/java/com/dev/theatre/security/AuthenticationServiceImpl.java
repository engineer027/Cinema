package com.dev.theatre.security;

import com.dev.theatre.model.Role;
import com.dev.theatre.model.User;
import com.dev.theatre.service.RoleService;
import com.dev.theatre.service.ShoppingCartService;
import com.dev.theatre.service.UserService;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final RoleService roleService;

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService,
                                     RoleService roleService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.roleService = roleService;
    }

    @Override
    public User register(User user) {
        Role role = roleService.getRoleByName("USER");
        user.setRoles(Set.of(role));
        shoppingCartService.registerNewShoppingCart(userService.add(user));
        return user;
    }
}

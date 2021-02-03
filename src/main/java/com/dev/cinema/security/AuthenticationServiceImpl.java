package com.dev.cinema.security;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.User;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;
    private ShoppingCartService shoppingCartService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        Optional<User> userFromDB = userService.findByLogin(login);
        if (userFromDB.isPresent()) {
            String hashPassword = HashUtil.hashPassword(password, userFromDB.get().getSalt());
            if (userFromDB.get().getPassword().equals(hashPassword)) {
                return userFromDB.get();
            }
        }
        throw new AuthenticationException("Incorrect login "
                + login + "or password" + password);
    }

    @Override
    public User register(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}

package com.dev.cinema.security;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> userFromDB = userService.findByEmail(email);
        String hashPassword = HashUtil.hashPassword(password, userFromDB.get().getSalt());
        if (userFromDB.isPresent() && userFromDB.get().getPassword().equals(hashPassword)) {
            return userFromDB.get();
        }
        throw new AuthenticationException("Incorrect login "+ email
                + "or password" + password);
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setLogin(email);
        byte[] salt = HashUtil.getSalt();
        user.setSalt(salt);
        String hashPassword = HashUtil.hashPassword(password, salt);
        user.setPassword(hashPassword);
        userService.add(user);
        return user;
    }
}

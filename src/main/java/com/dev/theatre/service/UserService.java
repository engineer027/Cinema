package com.dev.theatre.service;

import com.dev.theatre.model.User;
import java.util.Optional;

public interface UserService {
    User add(User user);

    Optional<User> findByLogin(String login);

    User get(Long userId);
}

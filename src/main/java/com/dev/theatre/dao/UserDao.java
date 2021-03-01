package com.dev.theatre.dao;

import com.dev.theatre.model.User;
import java.util.Optional;

public interface UserDao {
    User add(User user);

    Optional<User> findByLogin(String login);

    Optional<User> get(Long userId);
}

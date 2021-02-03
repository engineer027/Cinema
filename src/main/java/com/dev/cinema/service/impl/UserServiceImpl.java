package com.dev.cinema.service.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        byte[] salt = HashUtil.getSalt();
        user.setSalt(salt);
        String hashPassword = HashUtil.hashPassword(user.getPassword(), salt);
        user.setPassword(hashPassword);
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userDao.findByLogin(login);
    }
}

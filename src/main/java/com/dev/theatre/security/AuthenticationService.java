package com.dev.theatre.security;

import com.dev.theatre.model.User;

public interface AuthenticationService {
    User register(User user);
}

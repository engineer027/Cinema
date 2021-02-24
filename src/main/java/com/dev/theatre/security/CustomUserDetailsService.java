package com.dev.theatre.security;

import com.dev.theatre.model.User;
import com.dev.theatre.service.UserService;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.findByLogin(login).orElseThrow(()
                -> new UsernameNotFoundException("Could not find user by login " + login));
        UserBuilder userBuilder = null;
        userBuilder = org.springframework.security.core.userdetails.User.withUsername(login);
        userBuilder.password(user.getPassword());
        userBuilder.roles(user.getRoles()
                .stream()
                .map(role -> role.getRole().toString())
                .toArray(String[]::new));
        return userBuilder.build();
    }
}

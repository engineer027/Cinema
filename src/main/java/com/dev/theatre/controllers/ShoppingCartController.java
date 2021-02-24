package com.dev.theatre.controllers;

import com.dev.theatre.model.PerformanceSession;
import com.dev.theatre.model.User;
import com.dev.theatre.model.dto.ShoppingCartResponseDto;
import com.dev.theatre.service.PerformanceSessionService;
import com.dev.theatre.service.ShoppingCartService;
import com.dev.theatre.service.UserService;
import com.dev.theatre.service.mapper.ShoppingCartMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartMapper mapper;
    private final UserService userService;
    private final PerformanceSessionService performanceSessionService;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  ShoppingCartMapper mapper,
                                  UserService userService,
                                  PerformanceSessionService performanceSessionService) {
        this.shoppingCartService = shoppingCartService;
        this.mapper = mapper;
        this.userService = userService;
        this.performanceSessionService = performanceSessionService;
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto get(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByLogin(userDetails.getUsername()).get();
        return mapper.mapShoppingCartToResponseDto(shoppingCartService.getByUser(user));
    }

    @PostMapping("/performance-sessions")
    public void addPerformanceSession(Authentication authentication,
                                      @RequestParam Long performanceSessionId) {
        PerformanceSession performanceSession = performanceSessionService
                .get(performanceSessionId);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByLogin(userDetails.getUsername()).get();
        shoppingCartService.addSession(performanceSession, user);
    }
}

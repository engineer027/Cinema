package com.dev.cinema;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.time.LocalDateTime;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        Movie movieSoul = new Movie();
        movieSoul.setTitle("Soul");
        movieSoul.setDescription("Everybody has a soul. Joe Gardner is about to find his");
        MovieService movieService = (MovieService) injector
                .getInstance(MovieService.class);
        movieService.add(movieSoul);
        movieService.getAll().forEach(System.out::println);

        CinemaHall cinemaHallStandard = new CinemaHall();
        cinemaHallStandard.setCapacity(30);
        cinemaHallStandard.setDescription("3D");
        CinemaHallService cinemaHallService = (CinemaHallService) injector
                .getInstance(CinemaHallService.class);
        cinemaHallService.add(cinemaHallStandard);
        CinemaHall cinemaHallPremium = new CinemaHall();
        cinemaHallPremium.setCapacity(10);
        cinemaHallPremium.setDescription("4D");
        cinemaHallService.add(cinemaHallPremium);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession movieSessionSoul = new MovieSession();
        movieSessionSoul.setMovie(movieSoul);
        movieSessionSoul.setCinemaHall(cinemaHallStandard);
        LocalDateTime localDateTime = LocalDateTime.of(2021, 2, 1, 20, 0);
        movieSessionSoul.setShowTime(localDateTime);
        MovieSessionService movieSessionService = (MovieSessionService) injector
                .getInstance(MovieSessionService.class);
        movieSessionService.add(movieSessionSoul);
        MovieSession movieSessionSoulTwo = new MovieSession();
        movieSessionSoulTwo.setMovie(movieSoul);
        movieSessionSoulTwo.setCinemaHall(cinemaHallPremium);
        LocalDateTime localDateTime1 = LocalDateTime.of(2021, 2, 3, 12, 30);
        movieSessionSoulTwo.setShowTime(localDateTime1);
        movieSessionService.add(movieSessionSoulTwo);
        movieSessionService.findAvailableSessions(movieSoul.getId(), localDateTime.toLocalDate());

        User bob = new User();
        bob.setLogin("ssss@gmail.com");
        bob.setPassword("1234");
        UserService userService = (UserService) injector.getInstance(UserService.class);
        userService.add(bob);
        User user = userService.findByLogin("ssss@gmail.com").get();
        System.out.println(user);

        AuthenticationService authenticationService = (AuthenticationService) injector
                .getInstance(AuthenticationService.class);
        authenticationService.register("bob@gmail.com", "1234");
        User alice = authenticationService.register("alice@gmail.com", "1234");
        try {
            User login = authenticationService.login("bob@gmail.com", "1234");
            System.out.println(login.toString());
        } catch (AuthenticationException e) {
            System.out.println("login not work");
        }

        ShoppingCartService shoppingCartService = (ShoppingCartService) injector
                .getInstance(ShoppingCartService.class);
        ShoppingCart byUser = shoppingCartService.getByUser(alice);
        System.out.println(byUser.toString());
        shoppingCartService.addSession(movieSessionSoul, alice);
        OrderService orderService = (OrderService) injector
                .getInstance(OrderService.class);
        orderService.completeOrder(byUser);
        orderService.getOrdersHistory(alice);
    }
}

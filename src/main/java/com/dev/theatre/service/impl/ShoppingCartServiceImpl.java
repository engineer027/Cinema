package com.dev.theatre.service.impl;

import com.dev.theatre.dao.ShoppingCartDao;
import com.dev.theatre.dao.TicketDao;
import com.dev.theatre.model.PerformanceSession;
import com.dev.theatre.model.ShoppingCart;
import com.dev.theatre.model.Ticket;
import com.dev.theatre.model.User;
import com.dev.theatre.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private ShoppingCartDao shoppingCartDao;
    private TicketDao ticketDao;

    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao, TicketDao ticketDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.ticketDao = ticketDao;
    }

    @Override
    public void addSession(PerformanceSession performanceSession, User user) {
        Ticket ticket = new Ticket();
        ticket.setPerformanceSession(performanceSession);
        ticket.setUser(user);

        ShoppingCart usersShoppingCart = shoppingCartDao.getByUser(user);
        usersShoppingCart.getTickets().add(ticket);
        ticketDao.add(ticket);
        shoppingCartDao.update(usersShoppingCart);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartDao.add(shoppingCart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getTickets().clear();
        shoppingCartDao.update(shoppingCart);
    }
}

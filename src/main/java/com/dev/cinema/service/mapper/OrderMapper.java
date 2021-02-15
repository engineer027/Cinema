package com.dev.cinema.service.mapper;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.dto.OrderRequestDto;
import com.dev.cinema.model.dto.OrderResponseDto;
import com.dev.cinema.service.UserService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderMapper {
    private final UserService userService;

    public OrderMapper(UserService userService) {
        this.userService = userService;
    }

    public OrderResponseDto mapOrderToResponseDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(orderResponseDto.getId());
        orderResponseDto.setOrderDate(order.getOrderDate().toString());
        orderResponseDto.setTickets(order.getTickets());
        orderResponseDto.setUserId(order.getUser().getId());
        return orderResponseDto;
    }

    public Order mapRequestDtoToOrder(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setTickets(orderRequestDto.getTickets());
        order.setOrderDate(LocalDateTime.parse(orderRequestDto.getOrderDate(),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        order.setUser(userService.get(orderRequestDto.getUserId()));
        return order;
    }
}

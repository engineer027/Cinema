package com.dev.theatre.service.mapper;

import com.dev.theatre.model.Order;
import com.dev.theatre.model.dto.OrderResponseDto;
import com.dev.theatre.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final UserService userService;

    public OrderMapper(UserService userService) {
        this.userService = userService;
    }

    public OrderResponseDto mapOrderToResponseDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(orderResponseDto.getId());
        orderResponseDto.setOrderDate(order.getOrderDate().toString());
        List<Long> ticketsId = order.getTickets().stream()
                .map(p -> p.getId())
                .collect(Collectors.toList());
        orderResponseDto.setTicketsId(ticketsId);
        orderResponseDto.setUserId(order.getUser().getId());
        return orderResponseDto;
    }
}

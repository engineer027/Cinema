package com.dev.cinema.service.mapper;

import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.dto.ShoppingCartResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartMapper {
    public ShoppingCartResponseDto mapShoppingCartToResponseDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setId(shoppingCart.getId());
        List<Long> ticketsId = shoppingCart.getTickets().stream()
                .map(p -> p.getId())
                .collect(Collectors.toList());
        shoppingCartResponseDto.setTicketsId(ticketsId);
        return shoppingCartResponseDto;
    }
}

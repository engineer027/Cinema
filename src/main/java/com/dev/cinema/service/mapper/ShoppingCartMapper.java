package com.dev.cinema.service.mapper;

import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.dto.ShoppingCartResponseDto;

public class ShoppingCartMapper {
    public ShoppingCartResponseDto mapShoppingCartToResponseDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setId(shoppingCart.getId());
        shoppingCartResponseDto.setTickets(shoppingCart.getTickets());
        return shoppingCartResponseDto;
    }
}

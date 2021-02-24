package com.dev.theatre.service.mapper;

import com.dev.theatre.model.ShoppingCart;
import com.dev.theatre.model.dto.ShoppingCartResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
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

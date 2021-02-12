package com.dev.cinema.service.mapper;

import com.dev.cinema.model.dto.CinemaHallRequestDto;
import com.dev.cinema.model.dto.CinemaHallResponseDto;
import com.dev.cinema.model.CinemaHall;

public interface CinemaHallMapper {
    CinemaHallResponseDto mapCinemaHallToDto(CinemaHall cinemaHall);

    CinemaHall mapDtoToCinemaHall(CinemaHallRequestDto cinemaHallRequestDto);
}

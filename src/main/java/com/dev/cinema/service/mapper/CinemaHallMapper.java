package com.dev.cinema.service.mapper;

import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.dto.CinemaHallRequestDto;
import com.dev.cinema.model.dto.CinemaHallResponseDto;

public interface CinemaHallMapper {
    CinemaHallResponseDto mapCinemaHallToDto(CinemaHall cinemaHall);

    CinemaHall mapDtoToCinemaHall(CinemaHallRequestDto cinemaHallRequestDto);
}

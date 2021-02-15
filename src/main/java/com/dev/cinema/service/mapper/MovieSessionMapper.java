package com.dev.cinema.service.mapper;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.MovieSessionRequestDto;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.model.dto.MovieSessionUpdateDto;

public interface MovieSessionMapper {
    MovieSessionResponseDto mapMovieSessionToResponseDto(MovieSession movieSession);

    MovieSession mapRequestDtoToMovieSession(MovieSessionRequestDto movieSessionRequestDto);

    MovieSession mapUpdateDtoToMovieSession(MovieSessionUpdateDto movieSessionUpdateDto);
}

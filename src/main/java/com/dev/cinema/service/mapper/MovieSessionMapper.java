package com.dev.cinema.service.mapper;

import com.dev.cinema.model.dto.MovieSessionDeleteDto;
import com.dev.cinema.model.dto.MovieSessionRequestDto;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.model.dto.MovieSessionUpdateDto;
import com.dev.cinema.model.MovieSession;

public interface MovieSessionMapper {
    MovieSessionResponseDto mapMovieSessionToResponseDto(MovieSession movieSession);

    MovieSession mapRequestDtoToMovieSession(MovieSessionRequestDto movieSessionRequestDto);

    MovieSession mapUpdateDtoToMovieSession(MovieSessionUpdateDto movieSessionUpdateDto);

    MovieSession mapDeleteDtoToMovieSession(MovieSessionDeleteDto movieSessionDeleteDto);
}

package com.dev.cinema.service.mapper;

import com.dev.cinema.model.Movie;
import com.dev.cinema.model.dto.MovieRequestDto;
import com.dev.cinema.model.dto.MovieResponseDto;

public interface MovieMapper {
    MovieResponseDto mapMovieToDto(Movie movie);

    Movie mapDtoToMovie(MovieRequestDto movieRequestDto);
}

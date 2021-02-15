package com.dev.cinema.service.mapper;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.MovieSessionRequestDto;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    public MovieSessionMapper(MovieService movieService,
                              CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    public MovieSessionResponseDto mapMovieSessionToResponseDto(MovieSession movieSession) {
        MovieSessionResponseDto movieSessionResponseDto = new MovieSessionResponseDto();
        movieSessionResponseDto.setId(movieSession.getId());
        movieSessionResponseDto.setMovieId(movieSession.getMovie().getId());
        movieSessionResponseDto.setMovieTitle(movieSession.getMovie().getTitle());
        movieSessionResponseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        movieSessionResponseDto.setShowTime(movieSession.getShowTime().toString());
        return movieSessionResponseDto;
    }

    public MovieSession mapRequestDtoToMovieSession(MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setShowTime(LocalDateTime.parse(movieSessionRequestDto.getShowTime(),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        movieSession.setMovie(movieService.get(movieSessionRequestDto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService.get(movieSessionRequestDto
                .getCinemaHallId()));
        return movieSession;
    }
}

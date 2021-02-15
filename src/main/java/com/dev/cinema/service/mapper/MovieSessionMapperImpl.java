package com.dev.cinema.service.mapper;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.MovieSessionRequestDto;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.model.dto.MovieSessionUpdateDto;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapperImpl implements MovieSessionMapper {
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    public MovieSessionMapperImpl(MovieService movieService,
                                  CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    @Override
    public MovieSessionResponseDto mapMovieSessionToResponseDto(MovieSession movieSession) {
        MovieSessionResponseDto movieSessionResponseDto = new MovieSessionResponseDto();
        movieSessionResponseDto.setId(movieSession.getId());
        movieSessionResponseDto.setMovie(movieSession.getMovie());
        movieSessionResponseDto.setMovieTitle(movieSession.getMovie().getTitle());
        movieSessionResponseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        return movieSessionResponseDto;
    }

    @Override
    public MovieSession mapRequestDtoToMovieSession(MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setShowTime(movieSessionRequestDto.getShowTime());
        movieSession.setMovie(movieService.get(movieSessionRequestDto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService.get(movieSessionRequestDto
                .getCinemaHallId()));
        return movieSession;
    }

    @Override
    public MovieSession mapUpdateDtoToMovieSession(MovieSessionUpdateDto movieSessionUpdateDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setId(movieSessionUpdateDto.getId());
        movieSession.setShowTime(movieSessionUpdateDto.getShowTime());
        movieSession.setMovie(movieService.get(movieSessionUpdateDto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService.get(movieSessionUpdateDto
                .getCinemaHallId()));
        return movieSession;
    }

    @Override
    public MovieSession mapDeleteDtoToMovieSession(MovieSessionDeleteDto movieSessionDeleteDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setId(movieSessionDeleteDto.getId());
        movieSession.setShowTime(movieSessionDeleteDto.getShowTime());
        movieSession.setMovie(movieService.get(movieSessionDeleteDto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService.get(movieSessionDeleteDto
                .getCinemaHallId()));
        return movieSession;
    }
}

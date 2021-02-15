package com.dev.cinema.controllers;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.MovieSessionRequestDto;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.model.dto.MovieSessionUpdateDto;
import com.dev.cinema.service.MovieSessionService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper movieSessionMapper;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieSessionMapper movieSessionMapper) {
        this.movieSessionService = movieSessionService;
        this.movieSessionMapper = movieSessionMapper;
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> findAvailableSessions(
                                        @RequestParam Long movieId,
                                        @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy")
                                                LocalDate date) {
        return movieSessionService.findAvailableSessions(movieId, date)
                .stream()
                .map(movieSessionMapper::mapMovieSessionToResponseDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void create(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = movieSessionMapper
                .mapRequestDtoToMovieSession(movieSessionRequestDto);
        movieSessionService.add(movieSession);
    }

    @DeleteMapping
    public void delete(@RequestBody Long movieSessionId) {
        movieSessionService.delete(movieSessionId);

    }

    @PutMapping
    public void update(@RequestBody MovieSessionUpdateDto movieSessionUpdateDto) {
        MovieSession movieSession = movieSessionMapper
                .mapUpdateDtoToMovieSession(movieSessionUpdateDto);
        movieSessionService.update(movieSession);
    }
}

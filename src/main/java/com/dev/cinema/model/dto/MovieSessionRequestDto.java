package com.dev.cinema.model.dto;

import com.sun.istack.NotNull;
import javax.validation.constraints.Min;

public class MovieSessionRequestDto {
    @NotNull
    private String showTime;
    @NotNull
    @Min(1)
    private Long movieId;
    @NotNull
    @Min(1)
    private Long cinemaHallId;

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }
}

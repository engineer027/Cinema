package com.dev.theatre.service.mapper;

import com.dev.theatre.model.PerformanceSession;
import com.dev.theatre.model.dto.PerformanceSessionRequestDto;
import com.dev.theatre.model.dto.PerformanceSessionResponseDto;
import com.dev.theatre.service.PerformanceService;
import com.dev.theatre.service.TheatreStageService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class PerformanceSessionMapper {
    private final PerformanceService performanceService;
    private final TheatreStageService theatreStageService;

    public PerformanceSessionMapper(PerformanceService performanceService,
                                    TheatreStageService theatreStageService) {
        this.performanceService = performanceService;
        this.theatreStageService = theatreStageService;
    }

    public PerformanceSessionResponseDto mapSessionToResponseDto(PerformanceSession session) {
        PerformanceSessionResponseDto performanceSessionResponseDto =
                new PerformanceSessionResponseDto();
        performanceSessionResponseDto.setId(session.getId());
        performanceSessionResponseDto.setPerformanceId(session.getPerformance().getId());
        performanceSessionResponseDto.setPerformanceTitle(session.getPerformance().getTitle());
        performanceSessionResponseDto.setTheatreStageId(session.getTheatreStage().getId());
        performanceSessionResponseDto.setShowTime(session.getShowTime().toString());
        return performanceSessionResponseDto;
    }

    public PerformanceSession mapRequestDtoToPS(PerformanceSessionRequestDto sessionRequestDto) {
        PerformanceSession performanceSession = new PerformanceSession();
        performanceSession.setShowTime(LocalDateTime.parse(sessionRequestDto
                        .getShowTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        performanceSession.setPerformance(performanceService.get(sessionRequestDto
                .getPerformanceId()));
        performanceSession.setTheatreStage(theatreStageService.get(sessionRequestDto
                .getTheatreStageId()));
        return performanceSession;
    }
}

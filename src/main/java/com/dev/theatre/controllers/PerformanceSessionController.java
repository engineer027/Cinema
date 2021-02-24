package com.dev.theatre.controllers;

import com.dev.theatre.model.PerformanceSession;
import com.dev.theatre.model.dto.PerformanceSessionRequestDto;
import com.dev.theatre.model.dto.PerformanceSessionResponseDto;
import com.dev.theatre.service.PerformanceSessionService;
import com.dev.theatre.service.mapper.PerformanceSessionMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/performance-sessions")
public class PerformanceSessionController {
    private final PerformanceSessionService performanceSessionService;
    private final PerformanceSessionMapper performanceSessionMapper;

    public PerformanceSessionController(PerformanceSessionService performanceSessionService,
                                        PerformanceSessionMapper performanceSessionMapper) {
        this.performanceSessionService = performanceSessionService;
        this.performanceSessionMapper = performanceSessionMapper;
    }

    @GetMapping("/available")
    public List<PerformanceSessionResponseDto> findAvailableSessions(
            @RequestParam Long performanceId,
            @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy")
                    LocalDate date) {
        return performanceSessionService.findAvailableSessions(performanceId, date)
                .stream()
                .map(performanceSessionMapper::mapSessionToResponseDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void create(@RequestBody @Valid PerformanceSessionRequestDto
                               performanceSessionRequestDto) {
        PerformanceSession performanceSession = performanceSessionMapper
                .mapRequestDtoToPS(performanceSessionRequestDto);
        performanceSessionService.add(performanceSession);
    }

    @DeleteMapping("/{performanceSessionId}")
    public void delete(@PathVariable Long performanceSessionId) {
        performanceSessionService.delete(performanceSessionId);
    }

    @PutMapping("/{performanceSessionId}")
    public void update(@PathVariable Long performanceSessionId,
                       @RequestBody PerformanceSessionRequestDto
                               performanceSessionRequestDto) {
        PerformanceSession performanceSession = performanceSessionMapper
                .mapRequestDtoToPS(performanceSessionRequestDto);
        performanceSession.setId(performanceSessionId);
        performanceSessionService.update(performanceSession);
    }
}

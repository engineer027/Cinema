package com.dev.theatre.controllers;

import com.dev.theatre.model.Performance;
import com.dev.theatre.model.dto.PerformanceRequestDto;
import com.dev.theatre.model.dto.PerformanceResponseDto;
import com.dev.theatre.service.PerformanceService;
import com.dev.theatre.service.mapper.PerformanceMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/performance")
public class PerformanceController {
    private final PerformanceService performanceService;
    private final PerformanceMapper performanceMapper;

    public PerformanceController(PerformanceService performanceService,
                                 PerformanceMapper performanceMapper) {
        this.performanceService = performanceService;
        this.performanceMapper = performanceMapper;
    }

    @GetMapping
    public List<PerformanceResponseDto> getAll() {
        return performanceService.getAll()
                .stream()
                .map(performanceMapper::mapPerformanceToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void create(@RequestBody @Valid PerformanceRequestDto performanceRequestDto) {
        Performance performance = performanceMapper.mapDtoToPerformance(performanceRequestDto);
        performanceService.add(performance);
    }
}

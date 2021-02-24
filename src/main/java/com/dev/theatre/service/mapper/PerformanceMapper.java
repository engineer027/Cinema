package com.dev.theatre.service.mapper;

import com.dev.theatre.model.Performance;
import com.dev.theatre.model.dto.PerformanceRequestDto;
import com.dev.theatre.model.dto.PerformanceResponseDto;
import org.springframework.stereotype.Component;

@Component
public class PerformanceMapper {
    public PerformanceResponseDto mapPerformanceToDto(Performance performance) {
        PerformanceResponseDto performanceResponseDto = new PerformanceResponseDto();
        performanceResponseDto.setId(performance.getId());
        performanceResponseDto.setDescription(performance.getDescription());
        performanceResponseDto.setTitle(performance.getTitle());
        return performanceResponseDto;
    }

    public Performance mapDtoToPerformance(PerformanceRequestDto performanceRequestDto) {
        Performance performance = new Performance();
        performance.setTitle(performanceRequestDto.getTitle());
        performance.setDescription(performanceRequestDto.getDescription());
        return performance;
    }
}

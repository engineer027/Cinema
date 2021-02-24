package com.dev.theatre.service.mapper;

import com.dev.theatre.model.TheatreStage;
import com.dev.theatre.model.dto.TheatreStageRequestDto;
import com.dev.theatre.model.dto.TheatreStageResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TheatreStageMapper {
    public TheatreStageResponseDto mapTheatreStageToDto(TheatreStage theatreStage) {
        TheatreStageResponseDto theatreStageResponseDto = new TheatreStageResponseDto();
        theatreStageResponseDto.setDescription(theatreStage.getDescription());
        theatreStageResponseDto.setId(theatreStage.getId());
        theatreStageResponseDto.setCapacity(theatreStage.getCapacity());
        return theatreStageResponseDto;
    }

    public TheatreStage mapDtoToTheatreStage(TheatreStageRequestDto theatreStageRequestDto) {
        TheatreStage theatreStage = new TheatreStage();
        theatreStage.setDescription(theatreStageRequestDto.getDescription());
        theatreStage.setCapacity(theatreStageRequestDto.getCapacity());
        return theatreStage;
    }
}

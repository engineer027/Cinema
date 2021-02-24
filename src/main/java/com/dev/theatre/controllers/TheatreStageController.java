package com.dev.theatre.controllers;

import com.dev.theatre.model.TheatreStage;
import com.dev.theatre.model.dto.TheatreStageRequestDto;
import com.dev.theatre.model.dto.TheatreStageResponseDto;
import com.dev.theatre.service.TheatreStageService;
import com.dev.theatre.service.mapper.TheatreStageMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class TheatreStageController {
    private final TheatreStageService theatreStageService;
    private final TheatreStageMapper theatreStageMapper;

    public TheatreStageController(TheatreStageService theatreStageService,
                                  TheatreStageMapper theatreStageMapper) {
        this.theatreStageService = theatreStageService;
        this.theatreStageMapper = theatreStageMapper;
    }

    @GetMapping
    public List<TheatreStageResponseDto> getAll() {
        return theatreStageService.getAll()
                .stream()
                .map(theatreStageMapper::mapTheatreStageToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void create(@RequestBody @Valid TheatreStageRequestDto theatreStageRequestDto) {
        TheatreStage theatreStage = theatreStageMapper.mapDtoToTheatreStage(theatreStageRequestDto);
        theatreStageService.add(theatreStage);
    }
}

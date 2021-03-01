package com.dev.theatre.model.dto;

public class PerformanceSessionResponseDto {
    private Long id;
    private Long performanceId;
    private String performanceTitle;
    private Long theatreStageId;
    private String showTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(Long performanceId) {
        this.performanceId = performanceId;
    }

    public String getPerformanceTitle() {
        return performanceTitle;
    }

    public void setPerformanceTitle(String performanceTitle) {
        this.performanceTitle = performanceTitle;
    }

    public Long getTheatreStageId() {
        return theatreStageId;
    }

    public void setTheatreStageId(Long theatreStageId) {
        this.theatreStageId = theatreStageId;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
}

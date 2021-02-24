package com.dev.theatre.model;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "performance_session")
public class PerformanceSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Performance performance;
    @ManyToOne
    private TheatreStage theatreStage;
    private LocalDateTime showTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    public TheatreStage getTheatreStage() {
        return theatreStage;
    }

    public void setTheatreStage(TheatreStage theatreStage) {
        this.theatreStage = theatreStage;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PerformanceSession that = (PerformanceSession) o;
        return Objects.equals(id, that.id)
                && Objects.equals(performance, that.performance)
                && Objects.equals(theatreStage, that.theatreStage)
                && Objects.equals(showTime, that.showTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, performance, theatreStage, showTime);
    }

    @Override
    public String toString() {
        return "PerformanceSession{" + "id="
                + id + ", performance="
                + performance + ", theatreStage="
                + theatreStage + ", showTime="
                + showTime + '}';
    }
}

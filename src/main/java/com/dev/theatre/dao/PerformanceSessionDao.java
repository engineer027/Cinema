package com.dev.theatre.dao;

import com.dev.theatre.model.PerformanceSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PerformanceSessionDao {

    PerformanceSession add(PerformanceSession performanceSession);

    List<PerformanceSession> findAvailableSessions(Long performanceId, LocalDate date);

    PerformanceSession update(PerformanceSession performanceSession);

    void delete(Long performanceSessionId);

    Optional<PerformanceSession> get(Long performanceSessionId);
}

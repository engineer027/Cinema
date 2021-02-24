package com.dev.theatre.service;

import com.dev.theatre.model.PerformanceSession;
import java.time.LocalDate;
import java.util.List;

public interface PerformanceSessionService {
    List<PerformanceSession> findAvailableSessions(Long performanceId, LocalDate date);

    PerformanceSession add(PerformanceSession session);

    PerformanceSession update(PerformanceSession performanceSession);

    void delete(Long performanceSessionId);

    PerformanceSession get(Long performanceSessionId);
}

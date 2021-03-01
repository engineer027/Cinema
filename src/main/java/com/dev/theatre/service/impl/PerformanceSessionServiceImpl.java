package com.dev.theatre.service.impl;

import com.dev.theatre.dao.PerformanceSessionDao;
import com.dev.theatre.model.PerformanceSession;
import com.dev.theatre.service.PerformanceSessionService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PerformanceSessionServiceImpl implements PerformanceSessionService {
    private PerformanceSessionDao performanceSessionDao;

    public PerformanceSessionServiceImpl(PerformanceSessionDao performanceSessionDao) {
        this.performanceSessionDao = performanceSessionDao;
    }

    @Override
    public List<PerformanceSession> findAvailableSessions(Long performanceId, LocalDate date) {
        return performanceSessionDao.findAvailableSessions(performanceId, date);
    }

    @Override
    public PerformanceSession add(PerformanceSession session) {
        return performanceSessionDao.add(session);
    }

    @Override
    public PerformanceSession update(PerformanceSession performanceSession) {
        return performanceSessionDao.update(performanceSession);
    }

    @Override
    public void delete(Long performanceSessionId) {
        performanceSessionDao.delete(performanceSessionId);
    }

    @Override
    public PerformanceSession get(Long performanceSessionId) {
        return performanceSessionDao.get(performanceSessionId).get();
    }
}

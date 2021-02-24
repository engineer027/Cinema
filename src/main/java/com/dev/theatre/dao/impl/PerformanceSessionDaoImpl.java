package com.dev.theatre.dao.impl;

import com.dev.theatre.dao.PerformanceSessionDao;
import com.dev.theatre.exceptions.DataProcessingException;
import com.dev.theatre.model.PerformanceSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PerformanceSessionDaoImpl implements PerformanceSessionDao {
    private final SessionFactory sessionFactory;

    public PerformanceSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public PerformanceSession add(PerformanceSession performanceSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(performanceSession);
            transaction.commit();
            return performanceSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert performance session entity "
                    + performanceSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<PerformanceSession> findAvailableSessions(Long performanceId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            Query<PerformanceSession> getPerformanceSession = session
                    .createQuery("SELECT p FROM PerformanceSession p "
                            + "WHERE p.performance.id = :performanceId "
                                    + "AND date_format(p.showTime, '%Y-%m-%d') "
                                    + "= :date",
                            PerformanceSession.class);
            getPerformanceSession.setParameter("performanceId", performanceId);
            getPerformanceSession.setParameter("date", DateTimeFormatter.ISO_LOCAL_DATE
                    .format(date));
            return getPerformanceSession.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t find available sessions where performance Id: "
                    + performanceId + " and Local date time: " + date, e);
        }
    }

    @Override
    public PerformanceSession update(PerformanceSession performanceSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(performanceSession);
            transaction.commit();
            return performanceSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update performance Session entity "
                    + performanceSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Long performanceSessionId) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            PerformanceSession performanceSession = session
                    .load(PerformanceSession.class, performanceSessionId);
            session.delete(performanceSession);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't delete performance session id = "
                    + performanceSessionId, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<PerformanceSession> get(Long performanceSessionId) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(PerformanceSession.class, performanceSessionId));
        } catch (Exception e) {
            throw new RuntimeException("Can't get performance session with id: "
                    + performanceSessionId, e);
        }
    }
}

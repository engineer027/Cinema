package com.dev.theatre.dao.impl;

import com.dev.theatre.dao.PerformanceDao;
import com.dev.theatre.exceptions.DataProcessingException;
import com.dev.theatre.model.Performance;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PerformanceDaoImpl implements PerformanceDao {
    private final SessionFactory sessionFactory;

    public PerformanceDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Performance add(Performance performance) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(performance);
            transaction.commit();
            return performance;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert performance entity " + performance, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Performance> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Performance> getAllPerformances = session
                    .createQuery("from Performance", Performance.class);
            return getAllPerformances.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving performances.", e);
        }
    }

    @Override
    public Optional<Performance> get(Long performanceId) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Performance.class, performanceId));
        } catch (Exception e) {
            throw new RuntimeException("Can't get performance with id: " + performanceId, e);
        }
    }
}

package com.dev.theatre.dao.impl;

import com.dev.theatre.dao.TheatreStageDao;
import com.dev.theatre.exceptions.DataProcessingException;
import com.dev.theatre.model.TheatreStage;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class TheatreStageDaoImpl implements TheatreStageDao {
    private final SessionFactory sessionFactory;

    public TheatreStageDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public TheatreStage add(TheatreStage theatreStage) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(theatreStage);
            transaction.commit();
            return theatreStage;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Theatre Stage entity "
                    + theatreStage, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<TheatreStage> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<TheatreStage> getTheatreStage = session
                    .createQuery("from TheatreStage", TheatreStage.class);
            return getTheatreStage.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving Theatre Stage hall list", e);
        }
    }

    @Override
    public Optional<TheatreStage> get(Long theatreStageId) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(TheatreStage.class, theatreStageId));
        } catch (Exception e) {
            throw new RuntimeException("Can't get Theatre Stage hall with id: "
                    + theatreStageId, e);
        }
    }
}

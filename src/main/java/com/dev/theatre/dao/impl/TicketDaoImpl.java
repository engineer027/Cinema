package com.dev.theatre.dao.impl;

import com.dev.theatre.dao.TicketDao;
import com.dev.theatre.exceptions.DataProcessingException;
import com.dev.theatre.model.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl implements TicketDao {
    private final SessionFactory sessionFactory;

    public TicketDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Ticket add(Ticket ticket) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert ticked entity "
                    + ticket, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}

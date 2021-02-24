package com.dev.theatre.dao.impl;

import com.dev.theatre.dao.RoleDao;
import com.dev.theatre.exceptions.DataProcessingException;
import com.dev.theatre.model.Role;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {
    private final SessionFactory sessionFactory;

    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Role role) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert role entity "
                    + role, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Role> getRoleByName(String roleName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Role> getRole = session
                    .createQuery("SELECT r FROM Role r WHERE r.role = :roleName", Role.class);
            getRole.setParameter("roleName", roleName);
            return getRole.uniqueResultOptional();
        } catch (Exception e) {
            throw new RuntimeException("Can't get Role with name = " + roleName, e);
        }
    }
}

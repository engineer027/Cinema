package com.dev.theatre.dao.impl;

import com.dev.theatre.dao.UserDao;
import com.dev.theatre.exceptions.DataProcessingException;
import com.dev.theatre.model.User;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User add(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert user entity " + user, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> findByLogin(String login) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> getUser = session
                    .createQuery("SELECT u FROM User u join fetch u.roles "
                            + "WHERE u.login = :login", User.class);
            getUser.setParameter("login", login);
            return getUser.uniqueResultOptional();
        } catch (Exception e) {
            throw new RuntimeException("Can't get user with login = " + login, e);
        }
    }

    @Override
    public Optional<User> get(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(User.class, userId));
        } catch (Exception e) {
            throw new RuntimeException("Can't get User with id: " + userId, e);
        }
    }
}

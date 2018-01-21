package com.stasiamba.dao.hibernate;

import com.stasiamba.dao.GenericDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class ModelDAO<T> implements GenericDAO<T, Long> {

    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    private final Class<T> type;

    public ModelDAO(Class<T> type) {
        this.type = type;
    }

    private Class<T> getType() {
        return this.type;
    }

    public void create(T model) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(model);

        transaction.commit();
        session.close();
    }

    public T read(Long id) {
        Session session = this.sessionFactory.openSession();
        T model = session.get(getType(), id);

        model.toString();

        session.close();
        return model;
    }

    public void update(T model) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(model);

        transaction.commit();
        session.close();
    }

    public void delete(T model) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(model);

        transaction.commit();
        session.close();
    }

    public List<T> getAll() {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM " + getType().getSimpleName());
        List<T> list = query.list();

        for (T model : list){
            model.toString();
        }

        transaction.commit();
        session.close();

        return list;
    }
}

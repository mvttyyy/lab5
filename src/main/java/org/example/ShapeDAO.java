package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ShapeDAO {
    private final SessionFactory sessionFactory;

    public ShapeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session openSession() {
        return sessionFactory.openSession();
    }

    public void saveShape(Shape shape) {
        Transaction transaction = null;
        try (Session session = openSession()) {
            transaction = session.beginTransaction();
            session.persist(shape);
            transaction.commit();
        } catch (Exception err) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(err);
        }
    }

    public Shape getShapeById(Class<? extends Shape> shapeClass, int id) {
        try (Session session = openSession()) {
            return session.get(shapeClass, id);
        } catch (Exception err) {
            throw new RuntimeException(err);
        }
    }

    public <T extends Shape> List<T> getAllShapes(Class<T> shapeClass) {
        try (Session session = openSession()) {
            return session.createQuery("from " + shapeClass.getName(), shapeClass).list();
        } catch (Exception err) {
            throw new RuntimeException(err);
        }
    }

    public void deleteShape(Shape shape) {
        Transaction transaction = null;
        try (Session session = openSession()) {
            transaction = session.beginTransaction();
            session.remove(shape);
            transaction.commit();
        } catch (Exception err) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(err);
        }
    }

    public void updateShape(Shape shape) {
        Transaction transaction = null;
        try (Session session = openSession()) {
            transaction = session.beginTransaction();
            session.merge(shape);
            transaction.commit();
        } catch (Exception err) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(err);
        }
    }

    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}

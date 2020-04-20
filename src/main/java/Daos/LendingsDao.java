package Daos;

import Entity.Lending;
import HibernateConn.HibernateFactory;
import org.hibernate.Session;



public class LendingsDao<T> {

    private HibernateFactory hibernateFactory;

    public LendingsDao() {
        this.hibernateFactory = new HibernateFactory();
    }

    public void save(T element) {
        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.save(element);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("saveUser Error!");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
    public Lending readLending(Long id) {
        Session session = hibernateFactory.getSessionFactory().openSession();
        Lending lending = null;
        try {
            lending = session.get(Lending.class, id);
        } catch (Exception e) {
            System.out.println("readLending Error!");
        } finally {
            session.close();
        }
        return lending;
    }
    public void deleteLending(Long id)
    {
        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try
        {
            Lending lending = session.find(Lending.class, id);
            session.delete(lending);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("deleteLending Error");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}

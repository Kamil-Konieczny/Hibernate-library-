package Daos;

import Entity.Lending;
import HibernateConn.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;


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
    public List<Lending> readAllLendings()
    {
        List<Lending> lendingsList = new ArrayList<>();
        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Query query = session.createSQLQuery("SELECT *from lendings;");
            ((NativeQuery) query).addEntity(Lending.class);
            lendingsList =query.list();
        } catch (Exception e) {
            System.out.println("readAllLendings Error!");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return lendingsList;
    }

public void updateLendingsByBookId ( long book_i) {
    List<Lending> lendingList ;
    Session session = hibernateFactory.getSessionFactory().openSession();
    session.beginTransaction();
    try {
    Lending lending = session.find(Lending.class, book_i);
    lending.setStatus(true);

        session.getTransaction().commit();

    } catch (Exception e) {
        System.out.println("updateLendingsByBookID Error!");
        session.getTransaction().rollback();
    } finally {
        session.close();
    }
}
}
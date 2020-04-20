package Daos;

import Entity.Books;
import HibernateConn.HibernateFactory;
import org.hibernate.Session;



public class BooksDao<T> {

    private HibernateFactory hibernateFactory;

    public BooksDao() {
        this.hibernateFactory = new HibernateFactory();
    }

    public void save(T element) {
        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.save(element);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("saveBook Error!");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
    public Books readBook(Long id) {
        Session session = hibernateFactory.getSessionFactory().openSession();
        Books book = null;
        try {
            book = session.get(Books.class, id);
        } catch (Exception e) {
            System.out.println("readBook Error!");
        } finally {
            session.close();
        }
        return book;
    }
    public void deleteBook(Long id)
    {
        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try
        {
            Books book = session.find(Books.class, id);
            session.delete(book);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("deleteBook Error");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
    }

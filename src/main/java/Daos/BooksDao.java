package Daos;

import Entity.Books;
import HibernateConn.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


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
    public List<Books> readAllBooks()
    {
        List<Books> booksList = new ArrayList<>();
        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Query query = session.createSQLQuery("SELECT *from books;");
            ((NativeQuery) query).addEntity(Books.class);
            booksList =query.list();
    } catch (Exception e) {
        System.out.println("readAllBooks Error!");
        session.getTransaction().rollback();
    } finally {
        session.close();
    }
        return booksList;
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

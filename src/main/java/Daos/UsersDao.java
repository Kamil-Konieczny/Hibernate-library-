package Daos;

import Entity.Lending;
import Entity.Users;
import HibernateConn.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;


public class UsersDao<T> {

    private HibernateFactory hibernateFactory;

    public UsersDao() {
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
    public List<Users> readAllUsers()
    {
        List<Users> usersList = new ArrayList<>();
        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Query query = session.createSQLQuery("SELECT *from users;");
            ((NativeQuery) query).addEntity(Users.class);
            usersList =query.list();
        } catch (Exception e) {
            System.out.println("readAllUsers Error!");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return usersList;
    }
    public Users readUser(Long id) {
        Session session = hibernateFactory.getSessionFactory().openSession();
        Users user = null;
        try {
            user = session.get(Users.class, id);
        } catch (Exception e) {
            System.out.println("readUser Error!");
        } finally {
            session.close();
        }
        return user;
    }
    public void deleteUser(Long id)
    {
        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try
        {
            Users user = session.find(Users.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("deleteUser Error");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}

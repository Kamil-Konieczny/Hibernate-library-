package Daos;

import Entity.Users;
import HibernateConn.HibernateFactory;
import org.hibernate.Session;



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

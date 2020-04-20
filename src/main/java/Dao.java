
import Entity.Books;
import org.hibernate.Session;


public class Dao<T> {

    private HibernateFactory hibernateFactory;

    public Dao() {
        this.hibernateFactory = new HibernateFactory();
    }

    public void saveBook(T element) {
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
    public Books deleteBook(Long id)
    {
        Session session = hibernateFactory.getSessionFactory().openSession();
        try
        {

        } catch (Exception e) {
            System.out.println("deletebook Error!");
        }
    }
}
package HibernateConn;

import Daos.BooksDao;
import Daos.UsersDao;
import Entity.Books;
import Entity.Users;
import Swing.MainFrame;

import javax.swing.*;

public class Library_DB {



    public Library_DB() {
    }

    public static void main(String[] args) {
//        Books book = new Books();
//        book.setTitle( "Harry");
//        book.setAuthor_name("Kobe");
//        book.setAuthor_surname("Kabe");
//        BooksDao<Books> dao = new BooksDao();
//        dao.save(book);
//        Books s = dao.readBook(1L);
//        dao.deleteBook(2L);
//        Users user = new Users();
//        user.setResidence_place("Wolbrom");
//        user.setUser_name("Kamil");
//        user.setUser_surname("Konieczny");
//        UsersDao udao = new UsersDao();
//         udao.save(user);
        MainFrame framee = new MainFrame();
      JFrame frame = new JFrame("MainFrame");
      frame.setContentPane(new MainFrame().jPanel2);
      frame.pack();
        frame.setVisible(true);
    }
}

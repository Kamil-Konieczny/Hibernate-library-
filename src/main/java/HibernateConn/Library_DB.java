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
//        book.setStatus(true);
//        BooksDao<Books> dao = new BooksDao();
//        dao.save(book);
//        System.out.println(dao.readAllBooks().toString());
        MainFrame framee = new MainFrame();
      JFrame frame = new JFrame("MainFrame");
      frame.setContentPane(new MainFrame().jPanel2);
      frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    }
}

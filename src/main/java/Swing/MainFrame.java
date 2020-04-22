package Swing;

import Daos.BooksDao;
import Daos.LendingsDao;
import Daos.UsersDao;
import Entity.Books;
import Entity.Lending;
import Entity.Users;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MainFrame {
    private JTable table1;
    private JTable table2;
    private JLabel UsersLabel;
    private JTable table3;
    private JButton lendButton;
    private JTextField textField1;
    private JTextField textField2;
    public JPanel jPanel2;
    JScrollPane scrollPane1;

    public MainFrame() {
        initComponents();
        update_books_table();
        update_users_table();
        update_lendings_table();

    }

    public void initComponents() {
        scrollPane1 = new JScrollPane(table1);
        table1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "id", "author name", "author surrname", "title", "status"
                }
        ));

        table2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "id", "residence place", "user name", "user surrname"
                }
        ));
        table3.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "id", "user id", "book id", "date", "status"
                }
        ));
        lendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            int user_id=Integer.parseInt(textField1.getText());
            int book_id=Integer.parseInt(textField2.getText());
            LendingsDao lendingsDao = new LendingsDao();
            Lending lending= new Lending();
            lending.setUser_id((long) user_id);
            lending.setBook_id((long)book_id);

                LocalDateTime lt = LocalDateTime.now();
            lending.setDate_of_lending(lt);
            lendingsDao.save(lending);
            update_lendings_table();

    }}}

    private void update_books_table() {
        DefaultTableModel table1Model = (DefaultTableModel) table1.getModel();
        BooksDao booksDao = new BooksDao();
        List<Books> booksList = booksDao.readAllBooks();
        for (Books element : booksList) {
            table1Model.addRow(new Object[]{element.getId(), element.getAuthor_name(), element.getAuthor_surname(), element.getTitle(), element.isStatus()});
        }
        booksList.clear();
    }

    private void update_users_table() {
        DefaultTableModel table2Model = (DefaultTableModel) table2.getModel();
        UsersDao userDao = new UsersDao();
        List<Users> usersList = userDao.readAllUsers();
        for (Users element : usersList) {
            table2Model.addRow(new Object[]{element.getId(), element.getResidence_place(), element.getUser_name(), element.getUser_surname()});
        }
        usersList.clear();
    }
    private void update_lendings_table() {
        DefaultTableModel table3Model = (DefaultTableModel) table3.getModel();
        LendingsDao lendingsDao = new LendingsDao();
        List<Lending> lendingList = lendingsDao.readAllLendings();
        for(Lending element:lendingList) {
            table3Model.addRow(new Object[]{element.getId(),element.getUser_id(),element.getBook_id(),element.getDate_of_lending(),element.getStatus()});
        }
        lendingList.clear();
    }
}
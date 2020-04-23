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
import java.time.LocalDateTime;
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
    private JTextField addBookTitleField;
    private JTextField addBookANameField;
    private JTextField addBookASurnameField;
    private JButton AddBookButton;
    private JButton returnButton;
    private JTextField returnField;
    private JButton AddUserButton;
    private JTextField addUserNameField;
    private JTextField addUserSurnameField;
    private JTextField addUserPlaceField;
    private JScrollPane jScrollPane1;
    public MainFrame() {
        initComponents();
        update_books_table();
        update_users_table();
        update_lendings_table();
    }

    public void initComponents() {
        AddBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String AName = addBookANameField.getText();
                String ASurname = addBookASurnameField.getText();
                String Title = addBookTitleField.getText();
                if(AName!=null && ASurname !=null&&Title!=null) {
                    Books book = new Books();
                    book.setAuthor_name(AName);
                    book.setAuthor_surname(ASurname);
                    book.setTitle(Title);
                    BooksDao booksDao = new BooksDao();
                    booksDao.save(book);
                }
                update_books_table();
               }
        });
        AddUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String surname = addUserSurnameField.getText();
                String name = addUserNameField.getText();
                String residentialPlace = addUserPlaceField.getText();
                if(surname!=null && name !=null&&residentialPlace!=null) {
                    Users user = new Users();
                    user.setUser_name(name);
                    user.setUser_surname(surname);
                    user.setResidence_place(residentialPlace);

                    UsersDao usersDao = new UsersDao();
                    usersDao.save(user);
                }
                update_users_table();
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               long book_id = Integer.parseInt(returnField.getText());
                Books book ;
                BooksDao booksDao = new BooksDao();
                book = booksDao.readBook(book_id);
                book.setStatus(false);
                booksDao.updateBook(book);
                update_books_table();

                LendingsDao lendingsDao = new LendingsDao();
                Lending lending = new Lending();
                lending.setStatus(true);
                lendingsDao.updateLendingsByBookId(book_id);
                update_lendings_table();
            }
        });
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
            long user_id=Integer.parseInt(textField1.getText());
            long book_id=Integer.parseInt(textField2.getText());
            BooksDao booksDaoo = new BooksDao();
            UsersDao usersDaoo= new UsersDao();

            if(user_id!=0 && book_id!=0 && booksDaoo.readBook(book_id)!=null&&usersDaoo.readUser(user_id)!=null) {
                if(booksDaoo.readBook(book_id).getStatus()=="free") {
                    LendingsDao lendingsDao = new LendingsDao();
                    Lending lending = new Lending();
                    lending.setUser_id(user_id);
                    lending.setBook_id(book_id);
                    LocalDateTime lt = LocalDateTime.now();
                    lending.setDate_of_lending(lt);
                    lendingsDao.save(lending);
                    update_lendings_table();

                    BooksDao booksDao = new BooksDao();
                    Books book = new Books();
                    book.setStatus(true);
                    book.setId(book_id);

                    Books existBook = booksDao.readBook(book_id);
                    book.setTitle(existBook.getTitle());
                    book.setAuthor_surname(existBook.getAuthor_surname());
                    book.setAuthor_name(existBook.getAuthor_name());

                    booksDao.updateBook(book);
                    update_books_table();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"book already borrowed");
                }
            }
            else
            {JOptionPane.showMessageDialog(null,"wrong data");}
    }});}

    private void update_books_table() {
        DefaultTableModel table1Model = (DefaultTableModel) table1.getModel();
        table1Model.setRowCount(0);
        BooksDao booksDao = new BooksDao();
        List<Books> booksList = booksDao.readAllBooks();
        for (Books element : booksList) {
            table1Model.addRow(new Object[]{element.getId(), element.getAuthor_name(), element.getAuthor_surname(), element.getTitle(), element.getStatus()});
        }
        booksList.clear();
    }

    private void update_users_table() {
        DefaultTableModel table2Model = (DefaultTableModel) table2.getModel();
        table2Model.setRowCount(0);
        UsersDao userDao = new UsersDao();
        List<Users> usersList = userDao.readAllUsers();
        for (Users element : usersList) {
            table2Model.addRow(new Object[]{element.getId(), element.getResidence_place(), element.getUser_name(), element.getUser_surname()});
        }
        usersList.clear();
    }
    private void update_lendings_table() {
        DefaultTableModel table3Model = (DefaultTableModel) table3.getModel();
        table3Model.setRowCount(0);
        LendingsDao lendingsDao = new LendingsDao();
        List<Lending> lendingList = lendingsDao.readAllLendings();
        for(Lending element:lendingList) {
            table3Model.addRow(new Object[]{element.getId(),element.getUser_id(),element.getBook_id(),element.getDate_of_lending(),element.getStatus()});
        }
        lendingList.clear();
    }
}
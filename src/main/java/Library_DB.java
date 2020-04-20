import Entity.Books;

public class Library_DB {
    public Library_DB() {
    }

    public static void main(String[] args) {
        Books book = new Books();
        book.setTitle( "Harry");
        book.setAuthor_name("Kobe");
        book.setAuthor_surname("Kabe");
        Dao<Books> dao = new Dao();
        dao.saveBook(book);
        Books s = dao.readBook(1L);
    }
}

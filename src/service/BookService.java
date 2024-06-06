package service;
import dao.BookDAO;
import Model.Book;
import java.util.List;

public class BookService {

    private BookDAO bookDAO;

    public BookService() {
        this.bookDAO = new BookDAO();
    }

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    public void deleteBook(int bookId) {
        bookDAO.deleteBook(bookId);
    }

    public Book getBookById(int bookId) {
        return bookDAO.getBookById(bookId);
    }

    public void updateBookQuantity(int bookId, int quantity) {
        bookDAO.updateBookQuantity(bookId, quantity);
    }
}

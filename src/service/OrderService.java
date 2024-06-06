package service;
import dao.BookDAO;
import dao.OrderDAO;
import Model.Order;

public class OrderService {

    private OrderDAO orderDAO;
    private BookDAO bookDAO;

    public OrderService() {
        this.orderDAO = new OrderDAO();
        this.bookDAO = new BookDAO();
    }

    public void placeOrder(Order order) {
        // Check if the book is available
        var book = bookDAO.getBookById(order.getBookId());
        if (book != null && book.getQuantity() >= order.getQuantity()) {
            // Place the order
            orderDAO.placeOrder(order);

            // Update the book quantity
            bookDAO.updateBookQuantity(order.getBookId(), book.getQuantity() - order.getQuantity());
        } else {
            throw new IllegalArgumentException("Book is not available in the required quantity.");
        }
    }
}


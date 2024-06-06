import Model.Book;
import Model.Order;
import Model.User;
import service.BookService;
import service.OrderService;
import service.UserService;

import java.util.Scanner;

public class Main {

    private static UserService userService = new UserService();
    private static BookService bookService = new BookService();
    private static OrderService orderService = new OrderService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    loginUser(scanner);
                    break;
                case 3:
                    System.exit(0);

            }

        }
    }

    private static void registerUser(Scanner scanner) {
        System.out.println("Enter username:");
        String username = scanner.next();
        System.out.println("Enter password:");
        String password = scanner.next();
        System.out.println("Enter role (ADMIN/CUSTOMER):");
        String role = scanner.next();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        userService.register(user);
        System.out.println("User registered successfully!");
    }

    private static void loginUser(Scanner scanner) {
        System.out.println("Enter username:");
        String username = scanner.next();
        System.out.println("Enter password:");
        String password = scanner.next();

        User user = userService.login(username, password);

        if (user != null) {
            System.out.println("Login successful!");
            if (user.getRole().equals("ADMIN")) {
                adminMenu(scanner);
            } else {
                customerMenu(scanner, user);
            }
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private static void adminMenu(Scanner scanner) {
        while (true) {
            System.out.println("1. View All Books");
            System.out.println("2. Add Book");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Logout");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewAllBooks();
                    break;
                case 2:
                    addBook(scanner);
                    break;
                case 3:
                    updateBook(scanner);
                    break;
                case 4:
                    deleteBook(scanner);
                    break;
                case 5:
                    return;
            }
        }
    }

    private static void customerMenu(Scanner scanner, User user) {
        while (true) {
            System.out.println("1. View Available Books");
            System.out.println("2. Buy Book");
            System.out.println("3. Logout");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewAllBooks();
                    break;
                case 2:
                    buyBook(scanner, user);
                    break;
                case 3:
                    return;
            }
        }
    }

    private static void viewAllBooks() {
        var books = bookService.getAllBooks();
        for (var book : books) {
            System.out.println(book.getBookId() + ": " + book.getTitle() + " by " + book.getAuthor() + " - $" + book.getPrice() + " (Quantity: " + book.getQuantity() + ")");
        }
    }

    private static void addBook(Scanner scanner) {
        System.out.println("Enter title:");
        String title = scanner.next();
        System.out.println("Enter author:");
        String author = scanner.next();
        System.out.println("Enter price:");
        double price = scanner.nextDouble();
        System.out.println("Enter quantity:");
        int quantity = scanner.nextInt();

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPrice(price);
        book.setQuantity(quantity);

        bookService.addBook(book);
        System.out.println("Book added successfully!");
    }

    private static void updateBook(Scanner scanner) {
        System.out.println("Enter book ID to update:");
        int bookId = scanner.nextInt();

        Book book = bookService.getBookById(bookId);
        if (book != null) {
            System.out.println("Enter new title:");
            book.setTitle(scanner.next());
            System.out.println("Enter new author:");
            book.setAuthor(scanner.next());
            System.out.println("Enter new price:");
            book.setPrice(scanner.nextDouble());
            System.out.println("Enter new quantity:");
            book.setQuantity(scanner.nextInt());

            bookService.updateBook(book);
            System.out.println("Book updated successfully!");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void deleteBook(Scanner scanner) {
        System.out.println("Enter book ID to delete:");
        int bookId = scanner.nextInt();

        bookService.deleteBook(bookId);
        System.out.println("Book deleted successfully!");
    }

    private static void buyBook(Scanner scanner, User user) {
        System.out.println("Enter book ID to buy:");
        int bookId = scanner.nextInt();
        System.out.println("Enter quantity:");
        int quantity = scanner.nextInt();

        2. Buy Book
        3. Logout
        1
        1: javascript by james - $199
        Order order = new Order();
        order.setUserId(user.getUserId());
        order.setBookId(bookId);
        order.setQuantity(quantity);

        try {
            orderService.placeOrder(order);
            System.out.println("Book purchased successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

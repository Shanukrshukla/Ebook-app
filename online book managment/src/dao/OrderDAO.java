package dao;
import Model.Order;
import util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDAO {

    public void placeOrder(Order order) {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "INSERT INTO orders (user_id, book_id, quantity) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, order.getUserId());
            stmt.setInt(2, order.getBookId());
            stmt.setInt(3, order.getQuantity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

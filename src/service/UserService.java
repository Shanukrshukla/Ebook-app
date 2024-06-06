package service;
import dao.UserDAO;
import Model.User;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public User login(String username, String password) {
        return userDAO.getUserByUsernameAndPassword(username, password);
    }

    public void register(User user) {
        userDAO.registerUser(user);
    }
}

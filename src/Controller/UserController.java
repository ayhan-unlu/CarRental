package Controller;

import Model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    static List<User> userList = new ArrayList<>();
    static User user;

    public static void addDemoUsersToDB() {
        if (!isUserInDB("a")) {
            addUser("a", "a", "staff");
        }
        if (!isUserInDB("b")) {
            addUser("b", "b", "staff");
        }
        if (!isUserInDB("c")) {
            addUser("c", "c", "customer");
        }
    }

    public static boolean addUser(String username, String password, String type) {

        String query = "INSERT INTO users (username,password,type) VALUES(?,?,?)";
        User foundUser = DBHelper.getFetchByUsername(username);

        if (foundUser != null) {
            MessageHelper.showMessage("username already exists");
            return false;
        } else {
            try {
                PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, type);

                int response = preparedStatement.executeUpdate();
                if (response == -1) {
                    MessageHelper.showMessage("fail");
                    return false;
                }

                return response != -1;

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
    }

    public static User login(String username, String password) {
        User foundUser = DBHelper.getFetchByUsername(username);
        if (foundUser == null) {
            MessageHelper.showMessage("username not found");
            return null;
        } else {
            if ((foundUser.getPassword().equals(password))) {
                return foundUser;
            } else {
                MessageHelper.showMessage("wrong password");

                return null;
            }
        }

    }

    public static boolean isUserInDB(String username) {
        return DBHelper.getFetchByUsername(username) != null;
    }

}

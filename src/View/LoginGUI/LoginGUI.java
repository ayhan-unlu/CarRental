package View.LoginGUI;

import Controller.*;
import Helper.Config;
import Controller.GUIController;
import Helper.GUIHelper;
import Helper.MessageHelper;
import Model.User;

import javax.swing.*;

public class LoginGUI extends JFrame {
    private JPanel wrapper;
    private JPanel wrapper_top;
    private JPanel wrapper_bottom;
    private JLabel label_login_icon_patikadev;
    private JLabel label_login_welcome_message;
    private JLabel label_login_user_login;
    private JLabel label_login_user_username;
    private JTextField field_login_user_username;
    private JPasswordField field_login_user_password;
    private JLabel label_login_user_password;
    private JButton button_login_login;
    private JLabel label_login_new_user;
    private JButton button_login_sign_up;
    private static GUIController guiController;
    private static String username;
    private static String password;

    public LoginGUI() {
        add(wrapper);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        /// FOR QUICK ACCESS TO customer MENU
        field_login_user_username.setText("c");
        field_login_user_password.setText("c");
        /// FOR QUICK ACCESS TO customer MENU
        button_login_sign_up.addActionListener(e -> GUIController.runSignUpGUI());
        button_login_login.addActionListener(e -> {


            username = field_login_user_username.getText();
            password = new String(field_login_user_password.getPassword());
            if (username.isEmpty() || password.isEmpty()) {
                MessageHelper.showMessage("fill");
            } else {
                User foundUser = UserController.login(username, password);
                if (foundUser != null) {
                    ///  EXCLUDED TO A QUICK ACCESS TO STAFF MENU
                    //MessageHelper.showMessage("login");
                    ///  EXCLUDED TO A QUICK ACCESS TO STAFF MENU
                    if (foundUser.getType().equals("staff")) {
                        GUIController.runStaffGUI(foundUser);
                    }
                    if (foundUser.getType().equals("customer")) {
                        GUIController.runCustomerGUI(foundUser);
                    }
                }

            }
            GUIHelper.emptyTextFields(field_login_user_username, field_login_user_password);

        });
    }
}

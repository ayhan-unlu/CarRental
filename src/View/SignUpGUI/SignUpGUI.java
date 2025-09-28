package View.SignUpGUI;

import Controller.Config;
import Controller.GUIHelper;
import Controller.MessageHelper;
import Controller.UserController;

import javax.swing.*;

public class SignUpGUI extends JFrame {
    private JPanel wrapper;
    private JPanel wrapper_top;
    private JLabel label_signup_icon_patikadev;
    private JLabel label_signup_welcome_message;
    private JPanel wrapper_bottom;
    private JLabel label_signup_user_username;
    private JTextField field_signup_user_username;
    private JLabel label_signup_user_password;
    private JPasswordField field_signup_user_password;
    private JButton button_signup_signup;
    private JLabel label_signup_user_type;

    private String username;
    private String password;


    public SignUpGUI() {
        add(wrapper);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setTitle(Config.PROJECT_TITLE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        button_signup_signup.addActionListener(e -> {
            username = field_signup_user_username.getText();
            password = new String(field_signup_user_password.getPassword());
            if (username.isEmpty() || password.isEmpty()) {
                MessageHelper.showMessage("fill");
            } else {
                if (UserController.addUser(username, password, "customer")) {
                    MessageHelper.showMessage("signup");
                    dispose();
                } else {
                    MessageHelper.showMessage("fail");
                    GUIHelper.emptyTextFields(field_signup_user_username, field_signup_user_password);
                }
            }

        });
    }
}

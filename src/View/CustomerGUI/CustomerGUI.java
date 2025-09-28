package View.CustomerGUI;

import Controller.Config;
import Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerGUI extends JFrame {

    private JPanel wrapper;
    private JLabel label_customer_welcome;
    private JButton button_customer_logout;

    private final User user;

    public CustomerGUI(User user){
        this.user=user;
        add(wrapper);
        setSize(1200,700);
        setLocation(100,100);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        label_customer_welcome.setText("Welcome Customer "+user.getUsername());

        button_customer_logout.addActionListener(e -> {
            dispose();
        });
    }
}

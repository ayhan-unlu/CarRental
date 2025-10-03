package Helper;

import javax.swing.*;

public class MessageHelper {
    public static void showMessage(String message) {
        String title;
        switch (message) {
            case "fill":
                message = "Please fill all of the fields.";
                title = "Error";
                break;
            case "signup":
                message = "Sign up successful";
                title = "Information";
                break;
            case "login":
                message = "Login successful";
                title = "Information";
                break;
            case "fail":
                message = "Attempt Failed. Please try again.";
                title = "Error";
                break;
            default:
                title = "Information";
                break;
        }
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }
}

package View.StaffGUI;


import Controller.Config;
import Controller.VehicleController;
import Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffGUI extends JFrame {
    private JPanel wrapper;
    private JLabel label_staff_welcome;
    private JTabbedPane tabbed_panel_staff;
    private JPanel panel_staff_vehicle;
    private JButton button_staff_logout;
    private JPanel panel_staff_add_vehicle;
    private JLabel label_staff_add_vehicle;
    private JLabel label_staff_add_vehicle_type;
    private JComboBox combobox_staff_add_vehicle_type;
    private JLabel label_staff_add_vehicle_price;
    private JTextField field_staff_add_vehicle_price;
    private JLabel label_staff_add_vehicle_extra_driver;
    private JCheckBox checkbox_staff_add_vehicle_extra_driver;
    private JLabel label_staff_add_vehicle_extra_driver_price;
    private JTextField field_staff_add_vehicle_extra_driver_price;
    private JLabel label_staff_add_vehicle_baby_seat;
    private JCheckBox checkbox_staff_add_vehicle_baby_seat;
    private JLabel label_staff_add_vehicle_baby_seat_price;
    private JTextField field_staff_add_vehicle_baby_seat_price;
    private JButton button_staff_add_vehicle_add;
    private DefaultTableModel model_vehicle_list;
    private Object[] row_vehicle_list;

    public final User user;

    public StaffGUI(User user) {
        this.user = user;
        add(wrapper);
        setLocation(100, 100);
        setSize(1200, 700);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        if (user.getUsername().equals("a")) {
            label_staff_welcome.setText(" Welcome Staff " + user.getUsername() + " DRIVE IZMIR CAR RENTAL COMPANY");
        } else {
            label_staff_welcome.setText(" Welcome Staff " + user.getUsername() + " AUTO ISTANBUL CAR RENTAL COMPANY");
        }
        button_staff_logout.addActionListener(e -> dispose());
        button_staff_add_vehicle_add.addActionListener(e -> {
            VehicleController.addVehicle(user);


        });
    }

}

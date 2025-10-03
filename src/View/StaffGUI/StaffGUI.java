package View.StaffGUI;


import Controller.Config;
import Controller.GUIHelper;
import Controller.MessageHelper;
import Controller.VehicleController;
import Model.User;
import Model.Vehicle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class StaffGUI extends JFrame {
    private JPanel wrapper;
    private JLabel label_staff_welcome;
    private JTabbedPane tabbed_panel_staff;
    private JPanel panel_staff_vehicle;
    private JButton button_staff_logout;
    private JLabel label_staff_add_vehicle;
    private JLabel label_staff_add_vehicle_type;
    private JComboBox combobox_staff_add_vehicle_type;
    private JLabel label_staff_add_vehicle_summer_price;
    private JTextField field_staff_add_vehicle_summer_price;
    private JLabel label_staff_add_vehicle_extra_driver;
    private JCheckBox checkbox_staff_add_vehicle_extra_driver;
    private JLabel label_staff_add_vehicle_extra_driver_price;
    private JTextField field_staff_add_vehicle_extra_driver_price;
    private JLabel label_staff_add_vehicle_baby_seat;
    private JCheckBox checkbox_staff_add_vehicle_baby_seat;
    private JLabel label_staff_add_vehicle_baby_seat_price;
    private JTextField field_staff_add_vehicle_baby_seat_price;
    private JButton button_staff_add_vehicle_add;
    private JLabel label_staff_add_vehicle_winter_price;
    private JTextField field_staff_add_vehicle_winter_price;
    private JCheckBox checkbox_staff_add_vehicle_summer_available;
    private JCheckBox checkbox_staff_add_vehicle_winter_available;
    private JLabel label_staff_add_vehicle_summer_available;
    private JLabel label_staff_add_vehicle_winter_available;
    private JPanel panel_staff_add_vehicle;
    private JPanel panel_staff_reservation;
    private JTable table_staff_reservations_list;
    private JPanel panel_staff_reservation_delete;
    private JLabel label_statt_reservation_delete_title;
    private JLabel label_staff_reservation_delete_reservation_id;
    private JButton button_staff_reservation_delete;
    private JTable table_staff_vehicle_list;
    private JTextField field_staff_reservation_delete_reservation_id;
    private DefaultTableModel model_vehicle_list;
    private Object[] row_vehicle_list;

    public final User user;
    public static String type;
    public static int summer_price;
    public static int winter_price;
    public static boolean extra_driver;
    public static int extra_driver_price;
    public static boolean baby_seat;
    public static int baby_seat_price;
    public static boolean summer_available;
    public static boolean winter_available;


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
        emptyFields();

/// -------------------------- MODEL STAFF SEARCH VEHICLE LIST ------------------------------------------------------
        model_vehicle_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Object[] column_vehicle_list = {"Vehicle Id", "Company Id", "City", "Type", "Summer Available",  "Summer Price", "Winter Available", "Winter Price", "Extra Driver", "Extra Driver Price", "Baby Seat", "Baby Seat Price"};

        model_vehicle_list.setColumnIdentifiers(column_vehicle_list);

        row_vehicle_list = new Object[column_vehicle_list.length];

        table_staff_vehicle_list.setModel(model_vehicle_list);
        table_staff_vehicle_list.getTableHeader().setReorderingAllowed(false);
        loadStaffVehicleListModel(VehicleController.getVehicleListByCompany(user));




/// -----------------------### MODEL CUSTOMER SEARCH VEHICLE LIST ###---------------------------------------------------


/// ----------------------- MODEL CUSTOMER RESERVATION LIST ###---------------------------------------------------
/// -----------------------### MODEL CUSTOMER RESERVATION LIST ###---------------------------------------------------


        button_staff_logout.addActionListener(e -> dispose());

        button_staff_add_vehicle_add.addActionListener(e -> {

            type = combobox_staff_add_vehicle_type.getSelectedItem().toString();
            summer_available = checkbox_staff_add_vehicle_summer_available.isSelected();
            winter_available = checkbox_staff_add_vehicle_winter_available.isSelected();
            extra_driver = checkbox_staff_add_vehicle_extra_driver.isSelected();
            baby_seat = checkbox_staff_add_vehicle_baby_seat.isSelected();
            try {
                summer_price = Integer.parseInt(field_staff_add_vehicle_summer_price.getText().toString());
                winter_price = Integer.parseInt(field_staff_add_vehicle_winter_price.getText().toString());
                extra_driver_price = Integer.parseInt(field_staff_add_vehicle_extra_driver_price.getText().toString());
                baby_seat_price = Integer.parseInt(field_staff_add_vehicle_baby_seat_price.getText().toString());
            } catch (NumberFormatException e1) {
                MessageHelper.showMessage("Please enter a number in the price fields, if not available zero (0) is obligatory");
                return;
            }

            if (type.equals("") || (summer_price == 0 && winter_price == 0) || (field_staff_add_vehicle_summer_price.getText().isEmpty()) || field_staff_add_vehicle_winter_price.getText().isEmpty()) {
                MessageHelper.showMessage("Type and at least one seasonal price fields should be filled");

            } else if ((summer_available && summer_price == 0) || (winter_available && winter_price == 0)) {
                MessageHelper.showMessage("If available season is selected price has to be filled");
            } else if ((!summer_available && summer_price != 0) || (!winter_available && winter_price != 0)) {
                MessageHelper.showMessage("If season is not selected seasonal price should be set zero(0)");
            } else if ((extra_driver && extra_driver_price == 0) || (baby_seat && baby_seat_price == 0)) {
                MessageHelper.showMessage("If extra features are selected adding price is obligatory");
            } else if ((!extra_driver && extra_driver_price != 0) || (!baby_seat && baby_seat_price != 0)) {
                MessageHelper.showMessage("When extra features are not selected price should be set zero (0)");
            } else {
                if (VehicleController.addVehicle(user, type, summer_price, winter_price, extra_driver, extra_driver_price, baby_seat, baby_seat_price, summer_available, winter_available)) {
                    MessageHelper.showMessage("Vehicle added successfully");
                }
            }
            emptyFields();


        });

    }

    public void loadStaffVehicleListModel(ArrayList<Vehicle> vehicleList){
        DefaultTableModel clearModel = (DefaultTableModel)table_staff_vehicle_list.getModel();
        clearModel.setRowCount(0);

        int i;

        for(Vehicle vehicle : vehicleList){
            i=0;

            row_vehicle_list[i++] = vehicle.getId();
            row_vehicle_list [i++] = vehicle.getCompany_id();
            row_vehicle_list [i++] = vehicle.getCity();
            row_vehicle_list [i++] = vehicle.getType();
            row_vehicle_list [i++] = vehicle.isSummer_available();
            row_vehicle_list [i++] = vehicle.getSummer_price();
            row_vehicle_list [i++] = vehicle.isWinter_available();
            row_vehicle_list [i++] = vehicle.getWinter_price();
            row_vehicle_list [i++] = vehicle.isExtra_driver();
            row_vehicle_list [i++] = vehicle.getExtra_driver_price();
            row_vehicle_list [i++] = vehicle.isBaby_seat();
            row_vehicle_list [i++] = vehicle.getBaby_seat_price();
            model_vehicle_list.addRow(row_vehicle_list);
        }
    }

    public void emptyFields() {
        GUIHelper.prepareStaffGUIEmptyValues(combobox_staff_add_vehicle_type, field_staff_add_vehicle_summer_price, field_staff_add_vehicle_winter_price, field_staff_add_vehicle_extra_driver_price, field_staff_add_vehicle_baby_seat_price);

    }

}

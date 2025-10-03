package View.CustomerGUI;

import Controller.*;
import Helper.Config;
import Helper.DBHelper;
import Helper.ItemHelper;
import Helper.MessageHelper;
import Model.Reservation;
import Model.User;
import Model.Vehicle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;

public class CustomerGUI extends JFrame {

    private JPanel wrapper;
    private JTabbedPane tabbed_panel_customer;
    private JLabel label_customer_welcome;
    private JButton button_customer_logout;
    private JPanel tabbed_panel_customer_search;
    private JScrollPane scroll_panel_customer_search;
    private JPanel panel_customer_search;
    private JLabel label_customer_search_title;
    private JLabel label_customer_search_city;
    private JComboBox combobox_customer_search_city;
    private JLabel label_customer_search_vehicle_type;
    private JComboBox combobox_customer_search_vehicle_type;
    private JLabel label_customer_search_pickup_date;
    private JTextField field_customer_search_pickup_date;
    private JTextField field_customer_search_dropoff_date;
    private JLabel label_customer_search_dropoff_date;
    private JButton button_customer_search;
    private JTable table_customer_vehicle_search_list;
    private JPanel panel_customer_search_reservation;
    private JLabel label_customer_search_reservation;
    private JLabel label_customer_search_reservation_vehicle_id;
    private JTextField field_customer_search_reservation_vehicle_id;
    private JButton button_customer_search_reservation_reserve;
    private JPanel panel_customer_reservation;
    private JTable table_customer_reservations_search_list;
    private JPanel panel_staff_reservation_delete;
    private JLabel label_customer_reservation_delete_title;
    private JLabel label_customer_reservation_delete_reservation_id;
    private JButton button_customer_reservation_delete;
    private JScrollPane scroll_pane_customer_reservation;
    private JTextField field_customer_reservation_delete_reservation_id;
    private DefaultTableModel model_customer_vehicle_search_list;
    private Object[] row_customer_search_list;
    private DefaultTableModel model_customer_reservations_search_list;
    private Object[] row_customer_reservation_search_list;


    private final User user;
    private boolean isSearchProper = false;

    public CustomerGUI(User user) {
        this.user = user;
        add(wrapper);
        setSize(1500, 700);
        setLocation(10, 100);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        label_customer_welcome.setText("Welcome Customer " + user.getUsername());

        button_customer_logout.addActionListener(e -> {
            dispose();
        });
        button_customer_search.addActionListener(e -> {

            String city = combobox_customer_search_city.getSelectedItem().toString();
            String type = combobox_customer_search_vehicle_type.getSelectedItem().toString();
            String pickup_date = field_customer_search_pickup_date.getText();
            String dropoff_date = field_customer_search_dropoff_date.getText();
            if (city.isEmpty() || type.isEmpty() || pickup_date.isEmpty() || dropoff_date.isEmpty()) {
                MessageHelper.showMessage("fill");
            } else {
                loadCustomerVehicleSearchListModel(VehicleController.searchInVehicleList(city, type));
                isSearchProper = true;
            }
        });
/// -------------------------- MODEL CUSTOMER SEARCH VEHICLE LIST ------------------------------------------------------
        model_customer_vehicle_search_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Object[] column_customer_search_list = {"Vehicle Id", "Company Id", "City", "Type", "Summer Available", "Summer Price", "Winter Available", "Winter Price", "Extra Driver", "Extra Driver Price", "Baby Seat", "Baby Seat Price"};

        model_customer_vehicle_search_list.setColumnIdentifiers(column_customer_search_list);
        row_customer_search_list = new Object[column_customer_search_list.length];
        loadVehicleCityComboBox(combobox_customer_search_city);
        table_customer_vehicle_search_list.setModel(model_customer_vehicle_search_list);
        table_customer_vehicle_search_list.getTableHeader().setReorderingAllowed(false);
        loadCustomerVehicleSearchListModel(VehicleController.getVehicleList());
//        field_customer_search_pickup_date.setText("12.06.2025");
//        field_customer_search_dropoff_date.setText("13.06.2025");

        table_customer_vehicle_search_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (isSearchProper) {
                    int selectedRow = table_customer_vehicle_search_list.rowAtPoint(e.getPoint());
                    table_customer_vehicle_search_list.setRowSelectionInterval(selectedRow, selectedRow);
                    String selectedVehicleId = table_customer_vehicle_search_list.getValueAt(selectedRow, 0).toString();
                    field_customer_search_reservation_vehicle_id.setText(selectedVehicleId);
                }
            }
        });
/// -----------------------### MODEL CUSTOMER SEARCH VEHICLE LIST ###---------------------------------------------------
/// ----------------------- MODEL CUSTOMER RESERVATION LIST ###---------------------------------------------------
        model_customer_reservations_search_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Object[] column_customer_reservation_search_list = {"Reservation Id", "Vehicle Id", "Company Id", "City", "Type", "User Id", "Username", "Pick-up Date", "Drop-off Date", "Extra Driver", "Baby Seat", "Price"};

        model_customer_reservations_search_list.setColumnIdentifiers(column_customer_reservation_search_list);
        row_customer_reservation_search_list = new Object[column_customer_reservation_search_list.length];
        table_customer_reservations_search_list.setModel(model_customer_reservations_search_list);
        table_customer_reservations_search_list.getTableHeader().setReorderingAllowed(false);

        loadCustomerReservationsSearchListModel(DBHelper.getReservationListWithDBHelperByUser(user));

        table_customer_reservations_search_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = table_customer_reservations_search_list.rowAtPoint(e.getPoint());
                table_customer_reservations_search_list.setRowSelectionInterval(selectedRow, selectedRow);
                String selectedReservationId = table_customer_reservations_search_list.getValueAt(selectedRow, 0).toString();
                field_customer_reservation_delete_reservation_id.setText(selectedReservationId);
            }
        });

/// -----------------------### MODEL CUSTOMER RESERVATION LIST ###---------------------------------------------------


        button_customer_search_reservation_reserve.addActionListener(e -> {
            int selected_vehicle_id = 0;
            selected_vehicle_id = Integer.parseInt(field_customer_search_reservation_vehicle_id.getText());
            String string_pickup_date = field_customer_search_pickup_date.getText();
            String string_dropoff_date = field_customer_search_dropoff_date.getText();
            LocalDate localDate_pickup_date = ReservationController.convertStringToLocalDate(string_pickup_date);
            LocalDate localDate_dropoff_date = ReservationController.convertStringToLocalDate(string_dropoff_date);

            if (isSearchProper && selected_vehicle_id != 0 && localDate_pickup_date.isBefore(localDate_dropoff_date)) {
                if (ReservationController.reserveVehicle(user, selected_vehicle_id, localDate_pickup_date, localDate_dropoff_date)
                ) {
                    MessageHelper.showMessage("Reserved");
                } else {
                    MessageHelper.showMessage("The vehicle is not available on the selected dates");
                }
            }
            loadCustomerReservationsSearchListModel(DBHelper.getReservationListWithDBHelperByUser(user));


        });
        button_customer_reservation_delete.addActionListener(e -> {
            int selected_reservation_id = 0;
            selected_reservation_id = Integer.parseInt(field_customer_reservation_delete_reservation_id.getText());
            if (selected_reservation_id != 0) {
                if (!ReservationController.controlCurrentDateForDeletingReservation(selected_reservation_id)) {
                    MessageHelper.showMessage("You cant delete the reservation when only one or less day left");
                    return;
                }
            }
            if (selected_reservation_id == 0) {
                MessageHelper.showMessage("Please select a reservation from table to delete ");
                return;
            } else {
                if (ReservationController.deleteReservationByReservationId(selected_reservation_id)
                ) {
                    MessageHelper.showMessage("Reservation has been deleted");
                    loadCustomerReservationsSearchListModel(DBHelper.getReservationListWithDBHelperByUser(user));
                }
            }
        });
    }

    public void loadVehicleCityComboBox(JComboBox combobox) {
        combobox.removeAllItems();
        combobox.addItem(new ItemHelper(0, ""));
        ArrayList<String> cityList = new ArrayList<>();
        int i = 1;
        for (Vehicle obj : DBHelper.getVehicleListWithDBHelper()) {
            String currentCity = obj.getCity();
            if (!cityList.contains(currentCity)) {
                cityList.add(currentCity);
                combobox.addItem(new ItemHelper(i++, currentCity));
            }
        }

    }

    public void loadCustomerVehicleSearchListModel(ArrayList<Vehicle> vehicleList) {
        DefaultTableModel clearModel = (DefaultTableModel) table_customer_vehicle_search_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Vehicle obj : vehicleList) {
            i = 0;
            row_customer_search_list[i++] = obj.getId();
            row_customer_search_list[i++] = obj.getCompany_id();
            row_customer_search_list[i++] = obj.getCity();
            row_customer_search_list[i++] = obj.getType();
            row_customer_search_list[i++] = obj.isSummer_available();
            row_customer_search_list[i++] = obj.getSummer_price();
            row_customer_search_list[i++] = obj.isWinter_available();
            row_customer_search_list[i++] = obj.getWinter_price();
            row_customer_search_list[i++] = obj.isExtra_driver();
            row_customer_search_list[i++] = obj.getExtra_driver_price();
            row_customer_search_list[i++] = obj.isBaby_seat();
            row_customer_search_list[i++] = obj.getBaby_seat_price();
            model_customer_vehicle_search_list.addRow(row_customer_search_list);
        }
    }

    public void loadCustomerReservationsSearchListModel(ArrayList<Reservation> reservationList) {
        DefaultTableModel clearModel = (DefaultTableModel) table_customer_reservations_search_list.getModel();
        clearModel.setRowCount(0);

        int i;

        for (Reservation obj : reservationList) {
            i = 0;
            row_customer_reservation_search_list[i++] = obj.getId();
            row_customer_reservation_search_list[i++] = obj.getVehicle_id();
            row_customer_reservation_search_list[i++] = obj.getCompany_id();
            row_customer_reservation_search_list[i++] = obj.getCity();
            row_customer_reservation_search_list[i++] = obj.getType();
            row_customer_reservation_search_list[i++] = obj.getUser_id();
            row_customer_reservation_search_list[i++] = obj.getUsername();
            row_customer_reservation_search_list[i++] = obj.getPickup_date();
            row_customer_reservation_search_list[i++] = obj.getDropoff_date();
            row_customer_reservation_search_list[i++] = obj.isExtra_driver();
            row_customer_reservation_search_list[i++] = obj.isBaby_seat();
            row_customer_reservation_search_list[i++] = obj.getPrice();
            model_customer_reservations_search_list.addRow(row_customer_reservation_search_list);
        }
    }
}

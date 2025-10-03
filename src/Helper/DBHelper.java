package Helper;

import Model.Company;
import Model.Reservation;
import Model.User;
import Model.Vehicle;

import java.util.ArrayList;

public class DBHelper {

    public static User getFetchByUsername(String username) {
        User foundUser = SQLHelper.fetchUserByUsername(username);
//        PreparedStatement preparedStatement = SQLHelper.prepareStatement2FetchUserByUsername(username);

        return foundUser;
    }

    public static Company getFetchByCompanyName(String name) {
        Company foundCompany = SQLHelper.fetchCompanyByName(name);
        return foundCompany;
    }

    public static void addCompanyWithDBHelper(String name, String city) {
        SQLHelper.addCompanyWithSQLHelper(name, city);
    }

    public static boolean addVehicleWithDBHelper(Vehicle vehicle) {
        return SQLHelper.addVehicleWithSQLHelper(vehicle);
    }

    public static ArrayList<Vehicle> getVehicleListWithDBHelper() {
        return SQLHelper.getVehicleListWithSQLHelper();
    }

    public static Vehicle getFetchByVehicleId(int vehicle_id) {
        for (Vehicle vehicle : getVehicleListWithDBHelper()) {
            if (vehicle.getId() == vehicle_id) {
                return vehicle;
            }
        }
        return null;
    }

    public static boolean addReservationWithDBHelper(Reservation reservation) {
        return SQLHelper.addReservationWithSQLHelper(reservation);
    }

    public static ArrayList<Reservation> getReservationListWithDBHelper() {
        return SQLHelper.getReservationListWithSQLHelper();
    }

    public static ArrayList<Reservation> getReservationListWithDBHelperByUser(User user) {
        ArrayList<Reservation> reservationListByUser = new ArrayList<>();
        for (Reservation reservation : getReservationListWithDBHelper()) {
            if (reservation.getUser_id() == user.getId()) {
                reservationListByUser.add(reservation);
            }
        }
        return reservationListByUser;
    }


    public static ArrayList<Reservation> getFetchReservationByVehicleId(int vehicle_id) {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        for (Reservation obj : getReservationListWithDBHelper()) {
            if (obj.getVehicle_id() == vehicle_id) {
                reservationList.add(obj);
            }
        }
        return reservationList;
    }

    public static void deleteReservationByReservationId(int reservation_id) {
        SQLHelper.deleteReservationByReservationId(reservation_id);

    }


}

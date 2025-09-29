package Controller;

import Model.Reservation;
import Model.User;
import Model.Vehicle;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReservationHelper {
    public static LocalDate convertStringToLocalDate(String string_date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(string_date, formatter);
    }

    public static boolean reserveVehicle(User user, int selected_vehicle_id, LocalDate localDate_pickup_date, LocalDate localDate_dropoff_date) {
        Vehicle vehicle = DBHelper.getFetchByVehicleId(selected_vehicle_id);
        if (vehicle == null) {
            return false;
        }
        Reservation reservation = DBHelper.getFetchReservationByVehicleId(selected_vehicle_id);
        int company_id = vehicle.getCompany_id();
        String city = vehicle.getCity();
        String type = vehicle.getType();
        int user_id = user.getId();
        String username = user.getUsername();

        boolean isExtra_driver = vehicle.isExtra_driver();
        boolean isBaby_seat = vehicle.isBaby_seat();

        int price = VehicleController.calculateVehiclePrice(vehicle, localDate_pickup_date, localDate_dropoff_date);
        Reservation reservation = new Reservation();
        reservation.setVehicle_id(selected_vehicle_id);
        reservation.setCompany_id(company_id);
        reservation.setCity(city);
        reservation.setType(type);
        reservation.setUser_id(user_id);
        reservation.setUsername(username);
        reservation.setPickup_date(localDate_pickup_date);
        reservation.setDropoff_date(localDate_dropoff_date);
        reservation.setExtra_driver(isExtra_driver);
        reservation.setBaby_seat(isBaby_seat);
        reservation.setPrice(price);


        return DBHelper.addReservationWithDBHelper(reservation);

    }

}

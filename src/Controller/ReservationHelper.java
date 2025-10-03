package Controller;

import Model.Reservation;
import Model.User;
import Model.Vehicle;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class ReservationHelper {
    public static LocalDate convertStringToLocalDate(String string_date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(string_date, formatter);
    }

    public static boolean reserveVehicle(User user, int selected_vehicle_id, LocalDate localDate_pickup_date, LocalDate localDate_dropoff_date) {
        if (!checkReservationAvailability(selected_vehicle_id, localDate_pickup_date, localDate_dropoff_date)) {
            return false;
            //do deny the reservation
        } else {

            Reservation reservation = createReservation(user, selected_vehicle_id, localDate_pickup_date, localDate_dropoff_date);
            return DBHelper.addReservationWithDBHelper(reservation);

            //do the reservation
        }


    }

    public static boolean checkReservationAvailability(int selected_vehicle_id, LocalDate pickup_date, LocalDate dropoff_date) {
        ArrayList<Reservation> reservationList = new ArrayList<>();


        reservationList = DBHelper.getFetchReservationByVehicleId(selected_vehicle_id);
        if (reservationList.isEmpty()) {
            return true;
        }
        for (Reservation reservation : reservationList) {

            boolean overlaps = ((reservation.getDropoff_date().isBefore(pickup_date)) || (reservation.getPickup_date().isAfter(dropoff_date)));
            System.out.println("Existing: " + reservation.getPickup_date() + " → " + reservation.getDropoff_date());

            if (!overlaps) {
                return false;
            }
        }
        return true;
    }

    public static Reservation createReservation(User user, int selected_vehicle_id, LocalDate localDate_pickup_date, LocalDate localDate_dropoff_date) {
        Vehicle vehicle = DBHelper.getFetchByVehicleId(selected_vehicle_id);
        if (vehicle != null) {

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


            return reservation;
        }
        return null;
    }

    public static void printReservationListByVehicleId(ArrayList<Reservation> reservationList) {
        for (Reservation reservation : reservationList) {
            System.out.println(reservation.toString());
        }
    }

    public static boolean controlCurrentDateForDeletingReservation(int reservation_id) {

        Reservation reservation = getFetchReservationByReservationId(reservation_id);
        if (reservation != null) {
            long dayBetween = ChronoUnit.DAYS.between(LocalDate.now(),reservation.getPickup_date());
            return dayBetween>1;
        }
        return false;
    }

    public static Reservation getFetchReservationByReservationId(int reservation_id) {

        for (Reservation obj : DBHelper.getReservationListWithDBHelper()) {
            if (obj.getId() == reservation_id) {
                return obj;
            }
        }

        return null;
    }

    public static boolean deleteReservationByReservationId(int reservation_id) {

        DBHelper.deleteReservationByReservationId(reservation_id);
        return true;
    }


}

package Controller;

import Helper.DBHelper;
import Model.User;
import Model.Vehicle;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class VehicleController {

    public static boolean addVehicle(User user, String type, int summer_price, int winter_price, boolean extra_driver, int extra_driver_price, boolean baby_seat, int baby_seat_price, boolean summer_available, boolean winter_available) {
        if(!summer_available){summer_price=0;}
        if(!winter_available){winter_price=0;}
        Vehicle vehicle = createVehicle(user, type, summer_price, winter_price, extra_driver, extra_driver_price, baby_seat, baby_seat_price, summer_available, winter_available);

        return DBHelper.addVehicleWithDBHelper(vehicle);
    }

    public static Vehicle createVehicle(User user, String type, int summer_price, int winter_price, boolean extra_driver, int extra_driver_price, boolean baby_seat, int baby_seat_price, boolean summer_available, boolean winter_available) {
        Vehicle vehicle = new Vehicle();

        if (user.getUsername().equals("a")) {
            vehicle.setCompany_id(1);
            vehicle.setCity("izmir");
        }
        if (user.getUsername().equals("b")) {
            vehicle.setCompany_id(2);
            vehicle.setCity("istanbul");
        }
        vehicle.setType(type);
        vehicle.setSummer_price(summer_price);
        vehicle.setWinter_price(winter_price);
        vehicle.setExtra_driver(extra_driver);
        vehicle.setExtra_driver_price(extra_driver_price);
        vehicle.setBaby_seat(baby_seat);
        vehicle.setBaby_seat_price(baby_seat_price);
        vehicle.setSummer_available(summer_available);
        vehicle.setWinter_available(winter_available);
       // System.out.println(vehicle.toString());
        return vehicle;
    }

    public static ArrayList<Vehicle> getVehicleList() {
        return DBHelper.getVehicleListWithDBHelper();
    }

    public static ArrayList<Vehicle> getVehicleListByCompany(User user){
        ArrayList<Vehicle> vehicleListByCompany = new ArrayList<>();
        int user_company_id=0;

        if (user.getUsername().equals("a")){
            user_company_id=1;
        }
        if(user.getUsername().equals("b")){
            user_company_id=2;
        }
        for(Vehicle vehicle : getVehicleList()){
            if(vehicle.getCompany_id()==user_company_id){
                vehicleListByCompany.add(vehicle);
            }
        }
        return vehicleListByCompany;
    }

    public static ArrayList<Vehicle> searchInVehicleList(String city, String type) {
        ArrayList<Vehicle> resultList = new ArrayList<>();
        for (Vehicle vehicle : getVehicleList()) {
            if (vehicle.getCity().equals(city) && vehicle.getType().equals(type)) {
                resultList.add(vehicle);

            }
        }
        return resultList;
    }

    public static int calculateVehiclePrice(Vehicle vehicle, LocalDate localDate_pickup_date, LocalDate localDate_dropoff_date) {
        int price = 0;
        LocalDate midYear = LocalDate.of(2025, 6, 1);
        int duration = (int) ChronoUnit.DAYS.between(localDate_pickup_date,localDate_dropoff_date);

        if (localDate_pickup_date.isBefore(midYear)) {
            price += duration*vehicle.getWinter_price();
        } else {
            price += duration*vehicle.getSummer_price();
        }

        if (vehicle.isExtra_driver()) {
            price += vehicle.getExtra_driver_price();
        }
        if (vehicle.isBaby_seat()) {
            price += vehicle.getBaby_seat_price();
        }

        return price;
    }


}

package Controller;

import Model.User;
import Model.Vehicle;

public class VehicleController {

    public static boolean addVehicle(User user, String type, int summer_price,int winter_price, boolean extra_driver, int extra_driver_price, boolean baby_seat, int baby_seat_price, boolean summer_available, boolean winter_available) {
        System.out.println("Adding Vehicle");
        Vehicle vehicle=createVehicle(user,type,summer_price,summer_price ,extra_driver,extra_driver_price,baby_seat,baby_seat_price, summer_available, winter_available);

        return DBHelper.addVehicleWithDBHelper(vehicle);
    }

    public static Vehicle createVehicle(User user, String type, int summer_price,int winter_price, boolean extra_driver, int extra_driver_price, boolean baby_seat, int baby_seat_price, boolean summer_available, boolean winter_available){
        Vehicle vehicle = new Vehicle();

        if(user.getUsername().equals("a")){
            vehicle.setCompany_id(1);
            vehicle.setCity("izmir");
        }
        if(user.getUsername().equals("b")){
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
        System.out.println(vehicle.toString());
        return vehicle;
    }




}

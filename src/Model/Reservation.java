package Model;

import java.time.LocalDate;

public class Reservation {
    private int id;
    private int vehicle_id;
    private int company_id;
    private String city;
    private String type;
    private int user_id;
    private String username;
    private LocalDate pickup_date;
    private LocalDate dropoff_date;
    private boolean extra_driver;
    private boolean baby_seat;
    private int price;

    public Reservation() {
    }

    public Reservation(int id, int vehicle_id, int company_id, String city, String type, int user_id, String username, LocalDate pickup_date, LocalDate dropoff_date, boolean extra_driver, boolean baby_seat, int price) {
        this.id = id;
        this.vehicle_id = vehicle_id;
        this.company_id = company_id;
        this.city = city;
        this.type = type;
        this.user_id = user_id;
        this.username = username;
        this.pickup_date = pickup_date;
        this.dropoff_date = dropoff_date;
        this.extra_driver = extra_driver;
        this.baby_seat = baby_seat;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getPickup_date() {
        return pickup_date;
    }

    public void setPickup_date(LocalDate pickup_date) {
        this.pickup_date = pickup_date;
    }

    public LocalDate getDropoff_date() {
        return dropoff_date;
    }

    public void setDropoff_date(LocalDate dropoff_date) {
        this.dropoff_date = dropoff_date;
    }

    public boolean isExtra_driver() {
        return extra_driver;
    }

    public void setExtra_driver(boolean extra_driver) {
        this.extra_driver = extra_driver;
    }

    public boolean isBaby_seat() {
        return baby_seat;
    }

    public void setBaby_seat(boolean baby_seat) {
        this.baby_seat = baby_seat;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", vehicle_id=" + vehicle_id +
                ", company_id=" + company_id +
                ", city='" + city + '\'' +
                ", type='" + type + '\'' +
                ", user_id=" + user_id +
                ", username='" + username + '\'' +
                ", pickup_date=" + pickup_date +
                ", dropoff_date=" + dropoff_date +
                ", extra_driver=" + extra_driver +
                ", baby_seat=" + baby_seat +
                ", price=" + price +
                '}';
    }
}

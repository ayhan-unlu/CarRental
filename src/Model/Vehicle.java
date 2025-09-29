package Model;

public class Vehicle {
    private int id;
    private int company_id;
    private String city;
    private String type;
    private int summer_price;
    private int winter_price;
    private boolean extra_driver;
    private int extra_driver_price;
    private boolean baby_seat;
    private int baby_seat_price;
    private boolean summer_available;
    private boolean winter_available;

    public Vehicle() {
    }

    public Vehicle(int id, int company_id, String city, String type, int summer_price,int winter_price, boolean extra_driver, int extra_driver_price, boolean baby_seat, int baby_seat_price, boolean summer_available, boolean winter_available) {
        this.id = id;
        this.company_id = company_id;
        this.city = city;
        this.type = type;
        this.summer_price = summer_price;
        this.winter_price = winter_price;
        this.extra_driver = extra_driver;
        this.extra_driver_price = extra_driver_price;
        this.baby_seat = baby_seat;
        this.baby_seat_price = baby_seat_price;
        this.summer_available = summer_available;
        this.winter_available = winter_available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getSummer_price() {
        return summer_price;
    }

    public void setSummer_price(int summer_price) {
        this.summer_price = summer_price;
    }

    public int getWinter_price() {
        return winter_price;
    }

    public void setWinter_price(int winter_price) {
        this.winter_price = winter_price;
    }

    public boolean isExtra_driver() {
        return extra_driver;
    }

    public void setExtra_driver(boolean extra_driver) {
        this.extra_driver = extra_driver;
    }

    public int getExtra_driver_price() {
        return extra_driver_price;
    }

    public void setExtra_driver_price(int extra_driver_price) {
        this.extra_driver_price = extra_driver_price;
    }

    public boolean isBaby_seat() {
        return baby_seat;
    }

    public void setBaby_seat(boolean baby_seat) {
        this.baby_seat = baby_seat;
    }

    public int getBaby_seat_price() {
        return baby_seat_price;
    }

    public void setBaby_seat_price(int baby_seat_price) {
        this.baby_seat_price = baby_seat_price;
    }

    public boolean isSummer_available() {
        return summer_available;
    }

    public void setSummer_available(boolean summer_available) {
        this.summer_available = summer_available;
    }

    public boolean isWinter_available() {
        return winter_available;
    }

    public void setWinter_available(boolean winter_available) {
        this.winter_available = winter_available;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", company_id=" + company_id +
                ", city='" + city + '\'' +
                ", type='" + type + '\'' +
                ", summer_price=" + summer_price +
                ", winter_price=" + winter_price +
                ", extra_driver=" + extra_driver +
                ", extra_driver_price=" + extra_driver_price +
                ", baby_seat=" + baby_seat +
                ", baby_seat_price=" + baby_seat_price +
                ", available=" + summer_available +
                '}';
    }
}

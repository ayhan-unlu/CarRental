package Model;

public class Vehicle {
    private int id;
    private int company_id;
    private String city;
    private String type;
    private int price;
    private boolean isExtra_driver;
    private int extra_driver_price;
    private boolean isBaby_seat;
    private int baby_seat_price;
    private boolean isavailabality;

    public Vehicle() {
    }

    public Vehicle(int id, int company_id, String city, String type, int price, boolean isExtra_driver, int extra_driver_price, boolean isBaby_seat, int baby_seat_price, boolean isavailabality) {
        this.id = id;
        this.company_id = company_id;
        this.city = city;
        this.type = type;
        this.price = price;
        this.isExtra_driver = isExtra_driver;
        this.extra_driver_price = extra_driver_price;
        this.isBaby_seat = isBaby_seat;
        this.baby_seat_price = baby_seat_price;
        this.isavailabality = isavailabality;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isExtra_driver() {
        return isExtra_driver;
    }

    public void setExtra_driver(boolean extra_driver) {
        isExtra_driver = extra_driver;
    }

    public int getExtra_driver_price() {
        return extra_driver_price;
    }

    public void setExtra_driver_price(int extra_driver_price) {
        this.extra_driver_price = extra_driver_price;
    }

    public boolean isBaby_seat() {
        return isBaby_seat;
    }

    public void setBaby_seat(boolean baby_seat) {
        isBaby_seat = baby_seat;
    }

    public int getBaby_seat_price() {
        return baby_seat_price;
    }

    public void setBaby_seat_price(int baby_seat_price) {
        this.baby_seat_price = baby_seat_price;
    }

    public boolean isIsavailabality() {
        return isavailabality;
    }

    public void setIsavailabality(boolean isavailabality) {
        this.isavailabality = isavailabality;
    }
}

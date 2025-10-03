package Controller;

public class QueryHelper {
    static int id = 0;
    static String username = "";
    static String password = "";
    static String type = "";

    static String query = "";

    public static String createGetFetchByUsernameQuery(String username) {
        return "SELECT * FROM users WHERE username = ?";
    }

    public static String createGetFetchByCompanyNameQuery(String name) {
        return "SELECT * FROM companies WHERE name = ?";
    }

    public static String createAddCompanyQuery() {
        return "INSERT INTO companies (name, city) VALUES (?, ?)";
    }

    public static String createAddVehicleQuery() {
        return "INSERT INTO vehicles (company_id, city, type, summer_price, winter_price, extra_driver, extra_driver_price, baby_seat, baby_seat_price, summer_available, winter_available) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
    }

    public static String createGetVehicleListQuery() {
        return "SELECT * FROM vehicles";
    }

    public static String createAddReservationQuery(){
        return "INSERT INTO reservations (vehicle_id, company_id, city, type, user_id, username, pickup_date, dropoff_date, extra_driver, baby_seat, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    public static String createGetReservationListQuery(){
        return "SELECT * FROM reservations";
    }

    public static String createDeleteReservationByReservationIdQuery(){
        return "DELETE FROM reservations WHERE id=? ";
    }
}

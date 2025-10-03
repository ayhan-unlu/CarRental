package Controller;

import Model.Company;
import Model.Reservation;
import Model.User;
import Model.Vehicle;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SQLHelper {


    public static User fetchUserByUsername(String username) {
        User user = new User();
        user = fetchUserByPreparedStatement(preparePreparedStatement2FetchUserByUsername(username));
        return user;
    }

    public static PreparedStatement preparePreparedStatement2FetchUserByUsername(String username) {

        String query = QueryHelper.createGetFetchByUsernameQuery(username);

        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setString(1, username);
            return preparedStatement;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static User fetchUserByPreparedStatement(PreparedStatement preparedStatement) {
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User obj = new User();
                obj.setId(resultSet.getInt("id"));
                obj.setUsername(resultSet.getString("username"));
                obj.setPassword(resultSet.getString("password"));
                obj.setType(resultSet.getString("type"));
                return obj;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Company fetchCompanyByName(String name) {
        Company company = new Company();
        company = fetchCompanyByPreparedStatement(preparePreparedStatement2FetchCompanyByName(name));
        return company;
    }

    public static PreparedStatement preparePreparedStatement2FetchCompanyByName(String name) {
        String query = QueryHelper.createGetFetchByCompanyNameQuery(name);
        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setString(1, name);
            return preparedStatement;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Company fetchCompanyByPreparedStatement(PreparedStatement preparedStatement) {
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Company obj = new Company();
                obj.setName(resultSet.getString("name"));
                obj.setCity(resultSet.getString("city"));
                return obj;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void addCompanyWithSQLHelper(String name, String city) {
        addCompanyWithPreparedStatement(preparePreparedStatement2AddCompany(name, city));
    }

    public static PreparedStatement preparePreparedStatement2AddCompany(String name, String city) {
        String query = QueryHelper.createAddCompanyQuery();

        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, city);
            return preparedStatement;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public static boolean addCompanyWithPreparedStatement(PreparedStatement preparedStatement) {
        try {
            int response = preparedStatement.executeUpdate();
            return response > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean addVehicleWithSQLHelper(Vehicle vehicle) {
        return addVehicleWithPreparedStatement(preparePreparedStatement2AddVehicle(vehicle));
    }

    public static PreparedStatement preparePreparedStatement2AddVehicle(Vehicle vehicle) {
        String query = QueryHelper.createAddVehicleQuery();

        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setInt(1, vehicle.getCompany_id());
            preparedStatement.setString(2, vehicle.getCity());
            preparedStatement.setString(3, vehicle.getType());
            preparedStatement.setInt(4, vehicle.getSummer_price());
            preparedStatement.setInt(5, vehicle.getWinter_price());
            preparedStatement.setBoolean(6, vehicle.isExtra_driver());
            preparedStatement.setInt(7, vehicle.getExtra_driver_price());
            preparedStatement.setBoolean(8, vehicle.isBaby_seat());
            preparedStatement.setInt(9, vehicle.getBaby_seat_price());
            preparedStatement.setBoolean(10, vehicle.isSummer_available());
            preparedStatement.setBoolean(11, vehicle.isWinter_available());
            return preparedStatement;

        } catch (SQLException e) {
            System.out.println("SQL Add vehicle" + e.getMessage());
        }
        return null;
    }

    public static boolean addVehicleWithPreparedStatement(PreparedStatement preparedStatement) {
        try {
            int response = preparedStatement.executeUpdate();
            return response > 0;
        } catch (SQLException e) {
            System.out.println("SQL ADD Vehicle statement" + e.getMessage());
        }
        return false;
    }

    public static ArrayList<Vehicle> getVehicleListWithSQLHelper() {


        return getVehicleListWithStatement(createStatement2GetVehicleList());
    }

    public static Statement createStatement2GetVehicleList() {


        try {
            return DBConnector.getInstance().createStatement();

        } catch (SQLException e) {
            System.out.println("SQL Get Vehicle List" + e.getMessage());
        }
        return null;
    }

    public static ArrayList<Vehicle> getVehicleListWithStatement(Statement statement) {
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        Vehicle obj;
        String query = QueryHelper.createGetVehicleListQuery();
        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                obj = new Vehicle();
                obj.setId(resultSet.getInt("id"));
                obj.setCompany_id(resultSet.getInt("company_id"));
                obj.setCity(resultSet.getString("city"));
                obj.setType(resultSet.getString("type"));
                obj.setSummer_available(resultSet.getBoolean("summer_available"));
                obj.setSummer_price(resultSet.getInt("summer_price"));
                obj.setWinter_available(resultSet.getBoolean("winter_available"));
                obj.setWinter_price(resultSet.getInt("winter_price"));
                obj.setExtra_driver(resultSet.getBoolean("extra_driver"));
                obj.setExtra_driver_price(resultSet.getInt("extra_driver_price"));
                obj.setBaby_seat(resultSet.getBoolean("baby_seat"));
                obj.setBaby_seat_price(resultSet.getInt("baby_seat_price"));
                vehicleList.add(obj);
            }
        } catch (SQLException e) {
            System.out.println("SQL getVehicleListWithStatement" + e.getMessage());
        }
        return vehicleList;
    }


    public static boolean addReservationWithSQLHelper(Reservation reservation) {
        return addReservationWithPreparedStatement(preparePreparedStatement2AddReservation(reservation));
    }

    public static PreparedStatement preparePreparedStatement2AddReservation(Reservation reservation) {
        String query = QueryHelper.createAddReservationQuery();
        Date sqlDate_pickup_date = SQLHelper.convertLocalDateToSQLDate(reservation.getPickup_date());
        Date sqlDate_dropoff_date = SQLHelper.convertLocalDateToSQLDate(reservation.getDropoff_date());

        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setInt(1, reservation.getVehicle_id());
            preparedStatement.setInt(2, reservation.getCompany_id());
            preparedStatement.setString(3, reservation.getCity());
            preparedStatement.setString(4, reservation.getType());
            preparedStatement.setInt(5, reservation.getUser_id());
            preparedStatement.setString(6, reservation.getUsername());
            preparedStatement.setDate(7, sqlDate_pickup_date);
            preparedStatement.setDate(8, sqlDate_dropoff_date);
            preparedStatement.setBoolean(9, reservation.isExtra_driver());
            preparedStatement.setBoolean(10, reservation.isBaby_seat());
            preparedStatement.setInt(11, reservation.getPrice());
            return preparedStatement;
        } catch (SQLException e) {
            System.out.println("SQL Add Reservation" + e.getMessage());
        }
        return null;
    }

    public static boolean addReservationWithPreparedStatement(PreparedStatement preparedStatement) {
        try {
            int response = preparedStatement.executeUpdate();
            return response > 0;
        } catch (SQLException e) {
            System.out.println("SQL Add Reservation prepared " + e.getMessage());
        }
        return false;
    }

    public static Date convertLocalDateToSQLDate(LocalDate localDate) {

        Date sqlDate = Date.valueOf(localDate);
        return sqlDate;
    }

    public static LocalDate convertSQLDateToLocalDate(Date date) {
        return date.toLocalDate();
    }

    public static ArrayList<Reservation> getReservationListWithSQLHelper() {
        return getReservationListWithStatement(createStatement2GetReservationList());
    }

    public static Statement createStatement2GetReservationList() {
        try {
            return DBConnector.getInstance().createStatement();
        } catch (SQLException e) {
            System.out.println("SQL Get ReservationList" + e.getMessage());
        }
        return null;
    }

    public static ArrayList<Reservation> getReservationListWithStatement(Statement statement) {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        Reservation obj;

        String query = QueryHelper.createGetReservationListQuery();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                obj = new Reservation();
                obj.setId(resultSet.getInt("id"));
                obj.setVehicle_id(resultSet.getInt("vehicle_id"));
                obj.setCompany_id(resultSet.getInt("company_id"));
                obj.setCity(resultSet.getString("city"));
                obj.setType(resultSet.getString("type"));
                obj.setUser_id(resultSet.getInt("user_id"));
                obj.setUsername(resultSet.getString("username"));
                obj.setPickup_date(SQLHelper.convertSQLDateToLocalDate(resultSet.getDate("pickup_date")));
                obj.setDropoff_date(SQLHelper.convertSQLDateToLocalDate(resultSet.getDate("dropoff_date")));
                obj.setExtra_driver(resultSet.getBoolean("extra_driver"));
                obj.setBaby_seat(resultSet.getBoolean("baby_seat"));
                obj.setPrice(resultSet.getInt("price"));
                reservationList.add(obj);

            }
        } catch (SQLException e) {
            System.out.println("SQL Get ReservationList with statement" + e.getMessage());
        }
        return reservationList;
    }

    public static boolean deleteReservationByReservationId(int reservation_id) {
        return deleteReservationByReservationIdWithPreparedStatement(preparePreparedStatement2DeleteReservationByReservationId(reservation_id));



    }

    public static PreparedStatement preparePreparedStatement2DeleteReservationByReservationId(int reservation_id) {
        String query = QueryHelper.createDeleteReservationByReservationIdQuery();

        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setInt(1, reservation_id);
            return preparedStatement;
        } catch (SQLException e) {
            System.out.println("SQL Delete ReservationByReservationId" + e.getMessage());
        }
        return null;
    }

    public static boolean deleteReservationByReservationIdWithPreparedStatement(PreparedStatement preparedStatement){
        try{
            int response = preparedStatement.executeUpdate();
            return response>0;
        }catch(SQLException e){
            System.out.println("SQL Delete ReservationByReservationId" + e.getMessage());
        }
        return false;
    }
}
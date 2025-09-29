package Controller;

import Model.Company;
import Model.User;
import Model.Vehicle;

public class DBHelper {

    public static User getFetchByUsername(String username){
        User foundUser=SQLHelper.fetchUserByUsername(username);
//        PreparedStatement preparedStatement = SQLHelper.prepareStatement2FetchUserByUsername(username);

        return foundUser;
    }

    public static Company getFetchByCompanyName(String name){
        Company foundCompany = SQLHelper.fetchCompanyByName(name);
        return foundCompany;
    }

    public static void addCompanyWithDBHelper(String name, String city){
        SQLHelper.addCompanyWithSQLHelper(name,city);
    }

    public static boolean addVehicleWithDBHelper(Vehicle vehicle){
        return SQLHelper.addVehicleWithSQLHelper(vehicle);
    }




}

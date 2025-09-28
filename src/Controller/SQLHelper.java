package Controller;

import Model.Company;
import Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLHelper {


    public static User fetchUserByUsername(String username) {
        User user = new User();
        user = fetchUserByPreparedStatement(prepareStatement2FetchUserByUsername(username));
        return user;
    }

    public static PreparedStatement prepareStatement2FetchUserByUsername(String username) {

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
        company = fetchCompanyByPreparedStatement(prepareStatement2FetchCompanyByName(name));
        return company;
    }

    public static PreparedStatement prepareStatement2FetchCompanyByName(String name) {
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
        addCompanyWithPreparedStatement(prepareStatement2AddCompany(name, city));
    }

    public static PreparedStatement prepareStatement2AddCompany(String name, String city) {
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
        }return false;
    }
}
package Controller;

public class QueryHelper {
    static int id=0;
    static String username="";
    static String password="";
    static String type="";

    static String query="";

    public static String createGetFetchByUsernameQuery    (String username) {
        return "SELECT * FROM users WHERE username = ?";
    }

    public static String createGetFetchByCompanyNameQuery(String name){
        return "SELECT * FROM companies WHERE name = ?";
    }

    public static String createAddCompanyQuery(){
        return "INSERT INTO companies (name, city) VALUES (?, ?)";
    }
}

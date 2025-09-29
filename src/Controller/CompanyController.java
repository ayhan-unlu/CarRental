package Controller;

public class CompanyController {

    public static void addDefaultCompanies() {
        if (!isCompanyInDB("drive_izmir")) {
            addCompany("drive_izmir", "izmir");
        }
        if (!isCompanyInDB("auto_istanbul")) {
            addCompany("auto_istanbul", "istanbul");
        }
    }

    public static boolean isCompanyInDB(String name) {
        return DBHelper.getFetchByCompanyName(name) != null;
    }

    public static boolean addCompany(String name, String city) {

        DBHelper.addCompanyWithDBHelper(name, city);

        return false;
    }
}

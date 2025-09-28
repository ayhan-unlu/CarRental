package Controller;

import Model.User;
import View.CustomerGUI.CustomerGUI;
import View.LoginGUI.LoginGUI;
import View.SignUpGUI.SignUpGUI;
import View.StaffGUI.StaffGUI;

public class GUIController {

    public GUIController() {
        LoginGUI loginGUI = new LoginGUI();
        UserController.addDemoUsersToDB();
        CompanyController.addDefaultCompanies();
    }

    public static void runSignUpGUI() {
        setLayout();
        SignUpGUI signUpGUI = new SignUpGUI();
    }

    public static void runStaffGUI(User user) {
        setLayout();
        StaffGUI staffGUI = new StaffGUI(user);
    }

    public static void runCustomerGUI(User user) {
        CustomerGUI customerGUI = new CustomerGUI(user);
        setLayout();
    }

    public static void setLayout(){
        GUIHelper.setLayout();
    }
}

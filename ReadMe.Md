
![Java](https://img.shields.io/badge/Java-17%2B-orange)
![License: Free Use](https://img.shields.io/badge/License-Free--Use-green)![Patika.dev](https://img.shields.io/badge/Patika.dev-Project-blue)  
![GitHub stars](https://img.shields.io/github/stars/ayhan-unlu/PatikaAdvancedJavaProjects?style=social)
![GitHub forks](https://img.shields.io/github/forks/ayhan-unlu/PatikaAdvancedJavaProjects?style=social)
![GitHub last commit](https://img.shields.io/github/last-commit/ayhan-unlu/CarRental)
# Patika.dev Java Car Rental

## Description

### A simple Java desktop application for managing vehicle rentals and reservations. 

## The system allows:

- Staff to add vehicles, view and delete reservations

- Customers to browse vehicles, create and cancel reservations

- Automatic availability checks and reservation rules

- Data is stored in a MySQL database and automatically updated after each operation.


## Features

- Staff and customer login system

- Add vehicles, view, and cancel reservations (Staff)

- Search vehicles, make, view, and cancel reservations (Customer)

- Reservation cancellation limited to 1 day before pickup

- Menu-driven GUI using Java Swing
 (Main Menu → Login/Sign-Up → Customer/Staff Menu)

- MVC-based architecture for organized code

## Class Diagram
>>![ClassDiagram.png](View/ClassDiagram.png)

## UML s

### Class UML
![Class UML.png](View/Class%20UML.png)

### Controller UML
![ControllerUml.png](View/ControllerUml.png)

### Helper UML
![HelperUML.png](View/HelperUML.png)


### Menu UML
### Controller Helper UML

>>![ControllerHelperUml.png](ControllerHelperUml.png)





## Menus

### Login Menu
![Login Menu.png](src/View/Images/Login%20Menu.png)
### Sign Up Menu
![SignUp Menu.png](src/View/Images/SignUp%20Menu.png)
### Staff Menu
### Vehicle Panel
![StaffVehiclePanel.png](src/View/Images/StaffVehiclePanel.png)
### Reservation  Panel
![StaffReservationPanel.png](src/View/Images/StaffReservationPanel.png)
### Customer Menu
### Vehicle Panel
![CustomerVehiclePanel.png](src/View/Images/CustomerVehiclePanel.png)
### Reservation  Panel
![CustomerReservationPanel.png](src/View/Images/CustomerReservationPanel.png)
### DataBase Tables
![DB Tables.png](src/View/Images/DB%20Tables.png)

### User Table
![Users Table.png](src/View/Images/Users%20Table.png)

### Company Table
![Companies Table.png](src/View/Images/Companies%20Table.png)
### Vehicle Table
![Vehicles Table.png](src/View/Images/Vehicles%20Table.png)

### Reservation Table
![ReservationTable.png](src/View/Images/ReservationTable.png)




## License

This project is free to use, modify, and distribute.  
Citation is appreciated but not required.


## Getting Started
### Prerequisites

- Java 17+ installed

- IntelliJ IDEA or other Java IDE



### Running the Project

Clone the repository:

- git clone <https://github.com/ayhan-unlu/CarRental>

- Open the project in IntelliJ IDEA

- Run the Main class

- Follow login panel to sign up and login,



Test Accounts
- Username	Password	Role
-     a	    a	 Staff at Drive İzmir
-     b	    b	 Staff at Auto İstanbul
-     c	    c	 Customer


File Storage

- All user data is stored in MySQL database and automatically updated after every transaction.

## Project Structure

_250926_CarRental

    Controller
        CompanyController.java
        ReservationController.java
        UserController.java
        VehicleController.java
        GUIController.java

    Helper
        Config.java
        DBConnector.java
        DBHelper.java
        GUIHelper.java
        MessageHelper.java
        SQLHelper.java

    Model
        Company.java
        Reservation.java
        User.java
        Vehicle.java

    View
        CustomerGUI/CustomerGUI.java
        LoginGUI/LoginGUI.java
        SignUpGUI/SignUpGUI.java
        StaffGUI/StaffGUI.java│

    Main.java



## Future Improvements

- Add more edit option

- Add reservation history

- Improve GUI interface
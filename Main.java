package com.company;

import java.sql.*;

public class Main {
    public static final String DB_NAME = "Patients.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:D:\\databases\\" + DB_NAME;

    public static final String TABLE_PATIENTS = "Patients";
    public static final String COLOUMN_NAME = "name";
    public static final String COLOUMN_NUMBER = "number";
    public static final String COLOUMN_EMAIL = "email";
    public static final String COLOUMN_GENDER = "gender";
    public static final String COLOUMN_ADDRESS = "adress";


    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("DROP TABLE IF EXISTS " + TABLE_PATIENTS);
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_PATIENTS +
                    " (" + COLOUMN_NAME + " text, " +
                    COLOUMN_NUMBER + " text, " +
                    COLOUMN_EMAIL + " text, " +
                    COLOUMN_GENDER + " text, " +
                    COLOUMN_ADDRESS + " text" + ")");

            addPatient(statement, "Rashaun", 770834231, "rd@gmail.com", "Male", "8378 Ray Rd. Roswell, GA 30022");
            addPatient(statement, "Joey", 404222231, "j@gmail.com", "Male", "23 Crossing Rd. Tifton, GA 30987");
            addPatient(statement, "Tom", 770367938, "t@gmail.com", "Male", "888 Soldier Blvd. Macon, GA 30322");
            addPatient(statement, "Brad", 774534265, "b@gmail.com", "Male", "9856 Alpharetta Rd. Alpharetta, GA 30022");
            addPatient(statement, "Simon", 870834098, "s@gmail.com", "Male", "1 Bowling Green Rd. Rockdale, GA 30043");
            addPatient(statement, "Chris", 770983894, "c@gmail.com", "Male", "5721 Left Eye Dr. East Point, GA 33322");
            updatePatientsName(statement, "Mark", "Chris");
            updatePatientsNumber(statement, 435354356, 774534265);
            updatePatientsEmail(statement, "tommy@gmail.com", "t@gmail.com");
            updatePatientsAddress(statement, "638 Water Rd. Austell, GA 39463", "23 Crossing Rd. Tifton, GA 30987");
            deletePatients(statement, "Brad");

            ResultSet result = statement.executeQuery("SELECT * FROM " + TABLE_PATIENTS);
            while (result.next()){
                System.out.println(result.getString(COLOUMN_NAME) + " | " +
                        result.getLong(COLOUMN_NUMBER )+ " | " +
                        result.getString(COLOUMN_EMAIL) + " | " +
                        result.getString(COLOUMN_GENDER)+ " | " +
                        result.getString(COLOUMN_ADDRESS));
            }

        } catch (SQLException e) {
            System.out.println("Something is wrong: " + e.getMessage());
        }
    }

    public static void addPatient(Statement statement, String name, long number, String email, String gender, String address) throws SQLException {

        statement.execute("INSERT INTO " + TABLE_PATIENTS +
                "(" + COLOUMN_NAME + ", " +
                COLOUMN_NUMBER + ", " +
                COLOUMN_EMAIL + ", " +
                COLOUMN_GENDER + ", " +
                COLOUMN_ADDRESS + ")" +
                "VALUES('" + name + "', " + number + ", '" + email + "', '" + gender + "', '" + address + "')");
    }

    public static void updatePatientsName(Statement statement, String newName, String orginialName) throws SQLException{

        statement.execute("UPDATE " + TABLE_PATIENTS + " SET " + COLOUMN_NAME + "='" + newName + "'" + " WHERE " +
                                COLOUMN_NAME + "= '" + orginialName +"'");
    }

    public static void updatePatientsNumber(Statement statement, int newNumber, int originalNumber) throws SQLException{

        statement.execute("UPDATE " + TABLE_PATIENTS + " SET " + COLOUMN_NUMBER + "='" + newNumber + "'" + " WHERE " +
                COLOUMN_NAME + "= '" + originalNumber +"'");
    }

    public static void updatePatientsEmail(Statement statement, String newEmail, String orginialEmail) throws SQLException{

        statement.execute("UPDATE " + TABLE_PATIENTS + " SET " + COLOUMN_EMAIL + "='" + newEmail + "'" + " WHERE " +
                COLOUMN_EMAIL + "= '" + orginialEmail +"'");
    }

    public static void updatePatientsAddress(Statement statement, String newAddress, String orginialAddress) throws SQLException{

        statement.execute("UPDATE " + TABLE_PATIENTS + " SET " + COLOUMN_ADDRESS + "='" + newAddress + "'" + " WHERE " +
                COLOUMN_ADDRESS + "= '" + orginialAddress +"'");
    }

    public static void deletePatients(Statement statement, String name) throws SQLException{

        statement.execute("DELETE FROM " + TABLE_PATIENTS  + " WHERE " +
                COLOUMN_NAME + "= '" + name +"'");
    }
}

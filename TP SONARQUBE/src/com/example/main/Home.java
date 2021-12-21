package com.example.main;

import com.example.model.City;
import com.example.service.DatabaseConnection;
import com.example.service.DatabaseService;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Home {

    public static final String USER = "root";
    private static final String MYWORD ="password";
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/citiesdb";

    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection(USER, MYWORD, JDBC_DRIVER, DB_URL);
        Connection conn = db.connect();
        try {
            if (args.length > 0) {
                int id = Integer.parseInt(args[0]);
                String name = args[1];
                int numTourist = Integer.parseInt(args[2]);
                String desc = args[3];
                City city1 = new City(id, name, numTourist, desc);
                DatabaseService.addCity(conn, city1);
            }
        }
        catch (NumberFormatException e) {
            Logger.getLogger("DatabaseService.class").log(Level.SEVERE, "Arguments" + args[0] + args[2] + " must be an integer.",e);
            System.exit(1);
        }
    }
}

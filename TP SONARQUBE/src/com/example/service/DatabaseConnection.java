package com.example.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {

    private String user;
    private String pass;
    private String jbdcDriver;
    private String dbUrl;
    static Logger logger=Logger.getLogger("DatabaseConnection.class");

    public DatabaseConnection(String user, String pass, String jbdcDriver, String dbUrl) {
        this.user = user;
        this.pass = pass;
        this.jbdcDriver = jbdcDriver;
        this.dbUrl = dbUrl;
    }

    public  Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl, user, pass);
            Class.forName(jbdcDriver);
        } catch (SQLException e) {

            logger.log(Level.WARNING, "Connexion echoué a la bdd",e);
        } catch (ClassNotFoundException e) {
            logger.log(Level.WARNING, "Class not found",e);
        }
        return conn;
    }

    public  void disconnect(Connection conn ) {
        try {
            conn.close();
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Déonnexion echoué de la bdd",e);
        }
    }

    public void createDb(Connection conn) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql =  "CREATE TABLE   City " +
                    "(idCity INTEGER not NULL, " +
                    " name VARCHAR(255), " +
                    " touristNumber INTEGER, " +
                    " description VARCHAR(255), " +
                    " PRIMARY KEY ( idCity ))";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch(SQLException se) {
            logger.log(Level.WARNING, "Problème contourné lors de l'execution de la sql",se);
        } catch(Exception e) {
            logger.log(Level.WARNING, "une exception",e);
        } finally {
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
                logger.log(Level.WARNING, "Probleme lors de la fermeture du statement",se2);
            }
        }
    }

}

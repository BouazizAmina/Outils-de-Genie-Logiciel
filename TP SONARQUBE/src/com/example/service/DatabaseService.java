package com.example.service;

import com.example.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseService {
    private static String idCity="idCity";
    private static String touristNumber="touristNumber";
    private static String description="description";
    private static String name="name";
    private static Logger logger = Logger.getLogger("DatabaseService.class");
    private static String closingError="An exception occured on closing";

    public static int addCity(Connection conn, City city) {
        PreparedStatement pstmt = null;
        int i = -1;
        try {
            String sql = "INSERT INTO City " + "VALUES (?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, city.getIdCity());
            pstmt.setString(2, city.getName());
            pstmt.setInt(3, city.getTouristNumber());
            pstmt.setString(4, city.getDescription());
            i= pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException se) {
            logger.log(Level.SEVERE, "An SQL exception occured",se);
        } catch (Exception e) {
            logger.log(Level.WARNING, "An exception occured",e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException se2) {
            logger.log(Level.WARNING, closingError,se2);
            }
        }
        return i;
    }


   public static City getCity(Connection conn,int idCity) {
        PreparedStatement pstmt = null;
        City city = new City();
       ResultSet rs=null;
        try {

            String sql = "SELECT * FROM City where idCity=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,idCity);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                city.setIdCity(rs.getInt(idCity));
                city.setName(rs.getString(name));
                city.setTouristNumber(rs.getInt(touristNumber));
                city.setDescription(rs.getString(description));
            }
            pstmt.close();
        } catch (SQLException se) {
            logger.log(Level.WARNING, closingError,se);
        } finally {
            closeStateSr(pstmt,rs);
        }
        return  city;
    }


    public static List<City> getCities(Connection conn) {
        Statement stmt = null;
        List<City> cities = new ArrayList<>();
        ResultSet rs=null;
        try {

            String sql = "SELECT * FROM City";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                City city = new City();
                city.setIdCity(rs.getInt(idCity));
                city.setName(rs.getString(name));
                city.setTouristNumber(rs.getInt(touristNumber));
                city.setDescription(rs.getString(description));
                cities.add(city);
            }
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            logger.log(Level.WARNING, closingError,se);
        } finally {
            closeStateSr(stmt,rs);
        }
        return  cities;
    }
// New method

    public static City getCityByName(Connection conn,String name) {
        PreparedStatement pstmt = null;
        City city = new City();
        ResultSet rs=null;
        try {

            String sql = "SELECT * FROM City where name= ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();

           while (rs.next()) {
                city.setIdCity(rs.getInt(idCity));
                city.setName(rs.getString(name));
                city.setTouristNumber(rs.getInt(touristNumber));
                city.setDescription(rs.getString(description));
            }
        } catch (SQLException se) {
            logger.log(Level.WARNING, closingError,se);
        } finally {
            closeStateSr(pstmt,rs);
        }
        return city;
    }
    private static void closeStateSr(Statement pstmt, ResultSet rs){
        try {
            if (pstmt != null) pstmt.close();
        } catch (SQLException se2) {
            logger.log(Level.WARNING, closingError,se2);
        }
        try{
            if(rs!=null)rs.close();
        }
        catch (SQLException throwables) {
            logger.log(Level.WARNING, closingError, throwables);
        }
    }

}

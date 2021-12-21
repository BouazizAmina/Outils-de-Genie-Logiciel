package com.test;

import com.example.service.DatabaseConnection;
import javafx.beans.binding.When;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static javafx.beans.binding.Bindings.when;

public class DatabaseConnectionTest {

    @Test(expected = ClassNotFoundException.class)
    public void connectionTest(){
        DatabaseConnection bdc=new DatabaseConnection("user","pass",null,"org.h2.Driver");
        bdc.connect();
    }

    @Test(expected = SQLException.class)
    public void ConnectionTest()
    {
        DatabaseConnection bdc=new DatabaseConnection("user","pass","org.h2.Driver",null);
        bdc.connect();
    }
}

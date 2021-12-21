package com.example.test;

import com.example.dao.DatabaseConnection;
import com.example.dao.OrderDao;
import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.entity.Orderline;
import com.example.entity.Product;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoTest {

    /*Créaton de la base de données*/
    static Connection connection;
    @BeforeClass
    public static void unit(){
        DatabaseConnection data= new DatabaseConnection("sa","","org.h2.Driver","jdbc:h2:mem:test");
        connection = data.connect();
        data.createDb(connection);
    }

    @Test
    public void insertGetOrder(){
        /*Création de ORDER*/
        Product product = new Product();
        product.setProductID("1");
        Orderline orderline = new Orderline(product, 2);
        ArrayList<Orderline> orderlineList = new ArrayList<Orderline>();
        orderlineList.add(orderline);
        Customer customer = new Customer("1", "amina", "bouaziz", "batna", "0665812520");
        Order order= new Order(3, orderlineList, customer);
        OrderDao orderDao= new OrderDao();
        orderDao.setConn(connection);
        /*Tester la méthode insertOrder et getOrderDetails*/
        orderDao.insertOrder(order);
        assertArrayEquals(orderlineList.toArray(),orderDao.getOrderDetails(order.getOrderNum()).toArray());
        /*Tester la méthode deleteOrderssss*/
        orderDao.deleteOrder(order.getOrderNum());
        assertNull(orderDao.getOrderDetails(order.getOrderNum()));
    }


}
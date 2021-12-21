package com.example.test;

import com.example.controller.IPayementManager;
import com.example.controller.IStockManager;
import com.example.controller.OrderManager;
import com.example.dao.DatabaseConnection;
import com.example.dao.OrderDao;
import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.entity.Orderline;
import com.example.entity.Product;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderManagerTest {
    @Test
    public void createOrderFalse(){
        /*Création de ORDER*/
        Product product = new Product();
        product.setProductID("1");
        Orderline orderline = new Orderline(product, 10);
        ArrayList<Orderline> orderlineList = new ArrayList<Orderline>();
        orderlineList.add(orderline);
        Customer customer = new Customer("1", "amina", "bouaziz", "batna", "0665812520");
        Order order= new Order(3, orderlineList, customer);

        /*Tester la méthode createOrder dans le cas où "qteStock<orderline.getOrderedQte()"*/
        OrderDao orderDao= Mockito.mock(OrderDao.class);
        IPayementManager iPayementManager = Mockito.mock(IPayementManager.class);
        IStockManager iStockManager = Mockito.mock(IStockManager.class);
        OrderManager orderManager = new OrderManager(orderDao,iStockManager,iPayementManager);

        when(iStockManager.getProductQte(orderline.getProduct())).thenReturn(4);
        Assert.assertFalse(orderManager.createOrder(order));

        /*Tester la méthode cancelOrder dans le cas où "isPaid == true" */
        when(iPayementManager.isPaid(3)).thenReturn(true);
        Assert.assertFalse(orderManager.cancelOrder(3));
    }

    @Test
    public void createOrderTrue(){
        /*Création de ORDER*/
        Product product = new Product();
        product.setProductID("1");
        Orderline orderline = new Orderline(product, 10);
        ArrayList<Orderline> orderlineList = new ArrayList<Orderline>();
        orderlineList.add(orderline);
        Customer customer = new Customer("1", "amina", "bouaziz", "batna", "0665812520");
        Order order= new Order(3, orderlineList, customer);

        /*Tester la méthode createOrder dans le cas où "qteStock>orderline.getOrderedQte()"*/
        OrderDao orderDao= Mockito.mock(OrderDao.class);
        IPayementManager iPayementManager = Mockito.mock(IPayementManager.class);
        IStockManager iStockManager = Mockito.mock(IStockManager.class);
        OrderManager orderManager = new OrderManager(orderDao,iStockManager,iPayementManager);

        when(iStockManager.getProductQte(orderline.getProduct())).thenReturn(12);
        Assert.assertTrue(orderManager.createOrder(order));
        verify(orderDao).insertOrder(order);
        verify(iStockManager).removeProductStock(orderlineList.get(0).getProduct(), orderlineList.get(0).getOrderedQte());

        /*Tester la méthode cancelOrder dans le cas où "isPaid == false" */
        when(iPayementManager.isPaid(3)).thenReturn(false);
        when(orderDao.getOrderDetails(3)).thenReturn(orderlineList);
        Assert.assertTrue(orderManager.cancelOrder(3));
        verify(orderDao).deleteOrder(3);
        verify(iStockManager).addProductStock(orderlineList.get(0).getProduct(), orderlineList.get(0).getOrderedQte());

    }
}
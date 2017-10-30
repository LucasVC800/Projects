/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.daotests;

import com.mycompany.flooringmastery.dao.FlooringMasteryDao;
import com.mycompany.flooringmastery.dao.FlooringMasteryTrainingOrderFileImpl;
import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.exceptions.FlooringMasteryPersistenceException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Lucas
 */
public class OrderDaoTest {
    
    FlooringMasteryDao orderdao = new FlooringMasteryTrainingOrderFileImpl();

    public OrderDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getAllOrders() {
        //Arrange
        Order order1 = new Order();
        order1.setOrderNumber(1);
        order1.setCustomerName("Lucas");
        order1.setState("OH");
        order1.setOrderDate(LocalDate.parse("2017-09-22"));
        order1.setProductType("Wood");
        order1.setArea(200.00);
        
        Order order2 = new Order();
        order2.setOrderNumber(2);
        order2.setCustomerName("Kevin");
        order2.setState("TX");
        order2.setOrderDate(LocalDate.parse("2017-09-21"));
        order2.setProductType("Linoleum");
        order2.setArea(600.00);

        //Act
        List<Order> orderList = new ArrayList<>();
        try{
            orderdao.addOrder(order1);
            orderdao.addOrder(order2);
            orderList = orderdao.getAllOrders();
        } catch (FlooringMasteryPersistenceException e){
            fail(e.getMessage());
        }
        
        //Assert
            assertNotNull(orderList);
        
            assertTrue(orderList.size() == 2);
    }

    @Test
    public void testGetOrdersByDate() {
        //Arrange

        //Act
        
        //Assert
    }

    @Test
    public void testAddOrder() {
        //Arrange
        Order order1 = new Order();
        order1.setOrderNumber(1);
        order1.setCustomerName("Lucas");
        order1.setState("OH");
        order1.setOrderDate(LocalDate.parse("2017-09-22"));
        order1.setProductType("Wood");
        order1.setArea(200.00);

        //Act
        Order result = new Order();
        try{
            result = orderdao.addOrder(order1);
        } catch (FlooringMasteryPersistenceException e){
            fail(e.getMessage());
        }
        
        //Assert
        assertNotNull(result);
        
        assertTrue(result.getProductType().equals("Wood"));
        
        Order expected = orderdao.getOrder(result.getOrderNumber());
        assertNotNull(expected);
    }

    @Test
    public void testGetOrder() {
        //Arrange

        //Act
        
        //Assert
    }

    @Test
    public void testRemoveOrder() {
        Order result = new Order();
        List<Order> orderList = new ArrayList<>();
        
        //Arrange
        Order order1 = new Order();
        order1.setOrderNumber(1);
        order1.setCustomerName("Lucas");
        order1.setState("OH");
        order1.setOrderDate(LocalDate.parse("2017-09-22"));
        order1.setProductType("Wood");
        order1.setArea(200.00);
        
        Order order2 = new Order();
        order2.setOrderNumber(2);
        order2.setCustomerName("Kevin");
        order2.setState("TX");
        order2.setOrderDate(LocalDate.parse("2017-09-21"));
        order2.setProductType("Linoleum");
        order2.setArea(600.00);        

        //Act
        try{
        orderdao.addOrder(order1);
        orderdao.addOrder(order2);
        orderdao.removeOrder(1);
        result = orderdao.getOrder(1);
        orderList = orderdao.getAllOrders();
        } catch (FlooringMasteryPersistenceException e){
            fail(e.getMessage());
        }
        
        //Assert
        assertNull(result);
        assertTrue(orderList.size() == 1);
        
        //Act
        try{
        orderdao.removeOrder(2);
        result = orderdao.getOrder(2);
        orderList = orderdao.getAllOrders();
        } catch (FlooringMasteryPersistenceException e){
            fail(e.getMessage());
        }
        
        assertNull(result);
        assertTrue(orderList.isEmpty());
    }

    @Test
    public void testUpdateOrder() throws FlooringMasteryPersistenceException {
        Order result = new Order();
        List<Order> orderList = new ArrayList<>();
        
        //Arrange
        Order order1 = new Order();
        order1.setOrderNumber(1);
        order1.setCustomerName("Lucas");
        order1.setState("OH");
        order1.setOrderDate(LocalDate.parse("2017-09-22"));
        order1.setProductType("Wood");
        order1.setArea(200.00);
        
        Order order2 = new Order();
        order2.setOrderNumber(1);
        order2.setCustomerName("Kevin");
        order2.setState("TX");
        order2.setOrderDate(LocalDate.parse("2017-09-22"));
        order2.setProductType("Linoleum");
        order2.setArea(600.00);  

        //Act
        orderdao.addOrder(order1);
        orderdao.editOrder(order2);
        result = orderdao.getOrder(1);
        orderList = orderdao.getAllOrders();
        
        //Assert       
        Double area = result.getArea();
        assertTrue(area == 600.00);
        
        assertTrue(orderList.size() == 1);
    }

    @Test
    public void testCreateOrderNum() {
        //Arrange

        //Act
        
        //Assert
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
//    List<Order> getAllOrders() throws FlooringMasteryPersistenceException;
//
//    public List<Order> getOrdersByDate(LocalDate a) throws FlooringMasteryPersistenceException;
//
//    void addOrder(Order order) throws FlooringMasteryPersistenceException;
//
//    Order getOrder(Integer orderNum);
//
//    Order removeOrder(LocalDate date, Integer orderNum) throws FlooringMasteryPersistenceException;
//
//    Order updateOrder(LocalDate date, Integer orderNum);
//
//    Integer createOrderNum();
//
//    void loadOrder() throws FlooringMasteryPersistenceException;
}

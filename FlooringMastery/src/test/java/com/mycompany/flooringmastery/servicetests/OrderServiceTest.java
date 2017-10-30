/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.servicetests;


import com.mycompany.flooringmastery.dao.FlooringMasteryDao;
import com.mycompany.flooringmastery.dao.FlooringMasteryProductDao;
import com.mycompany.flooringmastery.dao.FlooringMasteryProductDaoFileImpl;
import com.mycompany.flooringmastery.dao.FlooringMasteryTaxDao;
import com.mycompany.flooringmastery.dao.FlooringMasteryTaxDaoFileImpl;
import com.mycompany.flooringmastery.dao.FlooringMasteryTrainingOrderFileImpl;
import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.exceptions.FlooringMasteryPersistenceException;
import com.mycompany.flooringmastery.service.FlooringOrdersService;
import com.mycompany.flooringmastery.service.FlooringOrdersServiceImpl;
import java.math.BigDecimal;
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
public class OrderServiceTest {

    FlooringOrdersService orderservice;
    FlooringMasteryDao orderdao = new FlooringMasteryTrainingOrderFileImpl();
    FlooringMasteryProductDao productdao = new FlooringMasteryProductDaoFileImpl();
    FlooringMasteryTaxDao taxdao = new FlooringMasteryTaxDaoFileImpl();
    
    public OrderServiceTest() {
        this.orderservice = new FlooringOrdersServiceImpl(orderdao, productdao, taxdao);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
                try{
        List<Order> orderList = orderservice.getEveryOrder();
        orderList.clear();
        }catch (FlooringMasteryPersistenceException e){
            fail(e.getMessage());
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetEveryOrder() throws FlooringMasteryPersistenceException {
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
            orderservice.addOrder(order1);
            orderservice.addOrder(order2);
            orderList = orderservice.getEveryOrder();
        } catch (FlooringMasteryPersistenceException e){
            fail(e.getMessage());
        }
        
        //Assert
            assertNotNull(orderList);
        
            assertTrue(orderList.size() == 2);
    }

    @Test
    public void testAddOrder() throws FlooringMasteryPersistenceException {
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
            result = orderservice.addOrder(order1);
        } catch (FlooringMasteryPersistenceException e){
            fail(e.getMessage());
        }
        
        //Assert
        assertNotNull(result);
        
        assertTrue(result.getProductType().equals("Wood"));
        
        Order expected = orderservice.getOrder(result.getOrderNumber());
        assertNotNull(expected);
    }

    @Test
    public void testRemoveOrder() throws FlooringMasteryPersistenceException {
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
        orderservice.addOrder(order1);
        orderservice.addOrder(order2);
        orderservice.removeOrder(1);
        result = orderservice.getOrder(1);
        orderList = orderservice.getEveryOrder();
        } catch (FlooringMasteryPersistenceException e){
            fail(e.getMessage());
        }
        
        //Assert
        assertNull(result);
        assertTrue(orderList.size() == 1);
        
        //Act
        try{
        orderservice.removeOrder(2);
        result = orderservice.getOrder(2);
        orderList = orderservice.getEveryOrder();
        } catch (FlooringMasteryPersistenceException e){
            fail(e.getMessage());
        }
        
        assertNull(result);
        assertTrue(orderList.isEmpty());
    }

    @Test
    public void testEditOrder() throws FlooringMasteryPersistenceException {
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
        orderservice.addOrder(order1);
        orderservice.editOrder(order2);
        result = orderservice.getOrder(1);
        orderList = orderservice.getEveryOrder();
        
        //Assert       
        Double area = result.getArea();
        assertTrue(area == 600.00);
        
        assertTrue(orderList.size() == 1);
    }

    @Test
    public void testGetOrder() throws FlooringMasteryPersistenceException {
        Order result = new Order();    
        
        //Arrange
        Order order1 = new Order();
        order1.setOrderNumber(1);
        order1.setCustomerName("Lucas");
        order1.setState("OH");
        order1.setOrderDate(LocalDate.parse("2017-09-22"));
        order1.setProductType("Wood");
        order1.setArea(200.00);

        //Act
        try{
        orderservice.addOrder(order1);
        result = orderservice.getOrder(1);
        } catch (FlooringMasteryPersistenceException e){
            
        }
        
        //Assert
        assertNotNull(result);
        assertTrue(result.getState().equals("OH"));
    }

    @Test
    public void testCalculateMaterialCost() throws FlooringMasteryPersistenceException {
        Order result = new Order();

        //Arrange
        Order order1 = new Order();
        order1.setOrderNumber(1);
        order1.setCustomerName("Lucas");
        order1.setState("OH");
        order1.setOrderDate(LocalDate.parse("2017-09-22"));
        order1.setProductType("Wood");
        order1.setArea(200.00);

        //Act
        try{
        result = orderservice.addOrder(order1);
        } catch (FlooringMasteryPersistenceException e){
            
        }
        
        //Assert
        assertNotNull(result.getLaborCost());
        assertTrue(result.getLaborCost().equals(new BigDecimal("400.00")));
    }

    @Test
    public void testCalculateLaborCost() {
        Order result = new Order();

        //Arrange
        Order order1 = new Order();
        order1.setOrderNumber(1);
        order1.setCustomerName("Lucas");
        order1.setState("OH");
        order1.setOrderDate(LocalDate.parse("2017-09-22"));
        order1.setProductType("Wood");
        order1.setArea(200.00);

        //Act
        try{
        result = orderservice.addOrder(order1);
        } catch (FlooringMasteryPersistenceException e){
            
        }
        
        //Assert
        assertNotNull(result.getMaterialCost());
        assertTrue(result.getMaterialCost().equals(new BigDecimal("400.00")));   
    }

    @Test
    public void testCalculateTaxCost() {
        Order result = new Order();

        //Arrange
        Order order1 = new Order();
        order1.setOrderNumber(1);
        order1.setCustomerName("Lucas");
        order1.setState("OH");
        order1.setOrderDate(LocalDate.parse("2017-09-22"));
        order1.setProductType("Wood");
        order1.setArea(200.00);

        //Act
        try{
        result = orderservice.addOrder(order1);
        } catch (FlooringMasteryPersistenceException e){
            
        }
        
        //Assert
        assertNotNull(result.getTaxCost());
        assertTrue(result.getTaxCost().equals(new BigDecimal("52.00"))); 
    }

    @Test
    public void testCalculateTotalCost() {
        Order result = new Order();

        //Arrange
        Order order1 = new Order();
        order1.setOrderNumber(1);
        order1.setCustomerName("Lucas");
        order1.setState("OH");
        order1.setOrderDate(LocalDate.parse("2017-09-22"));
        order1.setProductType("Wood");
        order1.setArea(200.00);

        //Act
        try{
        result = orderservice.addOrder(order1);
        } catch (FlooringMasteryPersistenceException e){
            
        }
        
        //Assert
        assertNotNull(result.getTotalCost());
        assertTrue(result.getTotalCost().equals(new BigDecimal("852.00")));   
    }

    @Test
    public void setMode() {
        //Arrange

        //Act
        
        //Assert       
    }

    @Test
    public void testGetOrderNumber() {
        //Arrange

        //Act
        
        //Assert       
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
//    
//    List<Order> getAllOrders(LocalDate date)  throws FlooringMasteryPersistenceException;
//    
//    void addOrder(Order order)  throws FlooringMasteryPersistenceException;
//    
//    Order removeOrder(LocalDate date, Integer orderNum) throws FlooringMasteryPersistenceException;
//    
//    Order editOrder(Integer orderNum);
//    
//    Order getOrder(Integer orderNum)  throws FlooringMasteryPersistenceException;
//    
//    BigDecimal calculateMaterialCost(Product product, Double area);
//    
//    BigDecimal calculateLaborCost(Product product, Double area);
//    
//    BigDecimal calculateTaxCost(Taxes tax, BigDecimal materialCost, BigDecimal laborCost);
//    
//    BigDecimal calculateTotalCost(BigDecimal materialCost, BigDecimal laborCost, BigDecimal taxCost);
//    
//    void setMode(Integer i);
//    
//    Integer getOrderNumber();
}

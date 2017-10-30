/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.servicetests;

import com.mycompany.flooringmastery.dao.FlooringMasteryProductDao;
import com.mycompany.flooringmastery.dao.FlooringMasteryProductDaoFileImpl;
import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.exceptions.FlooringMasteryPersistenceException;
import com.mycompany.flooringmastery.service.FlooringOrdersProductService;
import com.mycompany.flooringmastery.service.FlooringOrdersProductServiceImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucas
 */
public class ProductServiceTest {
    
    FlooringOrdersProductService productservice;
    FlooringMasteryProductDao productdao = new FlooringMasteryProductDaoFileImpl();
    
    public ProductServiceTest() throws FlooringMasteryPersistenceException{
        this.productservice =  new FlooringOrdersProductServiceImpl(productdao);
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
        List<Product> productList = productservice.getAllProducts();
        productList.clear();
        }catch (FlooringMasteryPersistenceException e){
            fail(e.getMessage());
        }
    }
    
    @After
    public void tearDown() {
    }
    
   @Test
   public void testCreateProduct(){
        // Arrange
        Product product = new Product();
        product.setProductType("Wood");
        product.setMaterialCostPerSqFoot(new BigDecimal("2.00"));
        product.setLaborCostPerSqFoot(new BigDecimal("2.00"));
       
        //Act
        Product result = new Product();
        try{
            result = productservice.createProduct(product);
            
        } catch (FlooringMasteryPersistenceException e) {
            fail("testCreateProduct(): " + e.getMessage());
            
        }
        //Assert
        try{
            assertNotNull(result);
            
            assertTrue(result.getProductType().equals("Wood"));
            
            Product expected = productservice.getProduct(result.getProductType());
            assertNotNull(expected);
        } catch (FlooringMasteryPersistenceException e){
            fail("testCreateProduct(): " + e.getMessage());
        }
    }
    
    @Test
    public void testGetAllProducts(){
        // Arrange
        Product product1 = new Product();
        product1.setProductType("Wood");
        product1.setMaterialCostPerSqFoot(new BigDecimal("2.00"));
        product1.setLaborCostPerSqFoot(new BigDecimal("2.00")); 
        
        Product product2 = new Product();
        product2.setProductType("Carpet");
        product2.setMaterialCostPerSqFoot(new BigDecimal("3.00"));
        product2.setLaborCostPerSqFoot(new BigDecimal("3.00")); 
        
        //Act
        List<Product> productList = new ArrayList<>();
        try{
        productservice.createProduct(product1);
        productservice.createProduct(product2);
        productList = productservice.getAllProducts();
        } catch (FlooringMasteryPersistenceException e){
            fail("testGetAllProducts: " + e.getMessage());
        }
        
        //Assert
            assertNotNull(productList);
            
            assertTrue(productList.size() == 2);
    }
    
    @Test
    public void testGetProduct(){
        // Arrange
        Product product1 = new Product();
        product1.setProductType("Wood");
        product1.setLaborCostPerSqFoot(new BigDecimal("2.00"));
        product1.setMaterialCostPerSqFoot(new BigDecimal("2.00"));
        
        //Act
        Product result = new Product();
        try{
        productservice.createProduct(product1);
        result = productservice.getProduct("Wood");
        }catch (FlooringMasteryPersistenceException e){
            fail("testGetProduct: " + e.getMessage());
        }
        //Assert
        assertNotNull(result);
        assertTrue(result.getProductType().equals("Wood"));
    }
    
    @Test
    public void testRemoveProduct(){
        Product result = new Product();
        List<Product> productList = new ArrayList<>();
        
        // Arrange
        Product product1 = new Product();
        product1.setProductType("Wood");
        product1.setMaterialCostPerSqFoot(new BigDecimal("2.00"));
        product1.setLaborCostPerSqFoot(new BigDecimal("2.00")); 
        
        Product product2 = new Product();
        product2.setProductType("Carpet");
        product2.setMaterialCostPerSqFoot(new BigDecimal("3.00"));
        product2.setLaborCostPerSqFoot(new BigDecimal("3.00")); 
        
        //Act
        try{
        productservice.createProduct(product1);
        productservice.createProduct(product2);
        productservice.removeProduct("Wood");
        result = productservice.getProduct("Wood");
        productList = productservice.getAllProducts();
        } catch (FlooringMasteryPersistenceException e) {
            fail("testRemoveProduct: " + e.getMessage());
        }
        //Assert
        assertNull(result);
        assertTrue(productList.size() == 1);
        
        //Act
        try{
            productservice.removeProduct("Carpet");
            result = productservice.getProduct("Carpet");
            productList = productdao.getAllProducts();
        } catch (FlooringMasteryPersistenceException e){
            fail("testRemoveProduct: " + e.getMessage());
        }
        
        //Assert
        assertNull(result);
        assertTrue(productList.isEmpty());
    }
    
    @Test
    public void testUpdateProduct(){
        Product result = new Product();
        List<Product> resultList = new ArrayList<>();
        // Arrange
        Product product1 = new Product();
        product1.setProductType("Wood");
        product1.setMaterialCostPerSqFoot(new BigDecimal("2.00"));
        product1.setLaborCostPerSqFoot(new BigDecimal("2.00"));
        
        Product product2 = new Product();
        product2.setProductType("Wood");
        product2.setMaterialCostPerSqFoot(new BigDecimal("2.00"));
        product2.setLaborCostPerSqFoot(new BigDecimal("3.00"));
        
        //Act
        try{
         productservice.createProduct(product1);
         productservice.updateProduct(product2);
         result = productservice.getProduct("Wood");
         resultList = productservice.getAllProducts();
            
        } catch (FlooringMasteryPersistenceException e){
            fail("testUpdateProduct: " + e.getMessage());
        }
        //Assert
        BigDecimal costSqFt = result.getLaborCostPerSqFoot();
        assertTrue(costSqFt.equals(new BigDecimal("3.00")));
        
        assertTrue(resultList.size() == 1);
    }
    
        

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
//    Product createProduct(Product product) throws FlooringMasteryPersistenceException;
//    
//    List<Product> getAllProducts() throws FlooringMasteryPersistenceException;
//    
//    Product getProduct(String productType) throws FlooringMasteryPersistenceException;
//    
//    Product removeProduct (String productType) throws FlooringMasteryPersistenceException;
//    
//    Product editProduct (Product product) throws FlooringMasteryPersistenceException;
}

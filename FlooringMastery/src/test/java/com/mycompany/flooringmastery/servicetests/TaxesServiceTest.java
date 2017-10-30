/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.servicetests;

import com.mycompany.flooringmastery.dao.FlooringMasteryTaxDao;
import com.mycompany.flooringmastery.dao.FlooringMasteryTaxDaoFileImpl;
import com.mycompany.flooringmastery.dto.Taxes;
import com.mycompany.flooringmastery.exceptions.FlooringMasteryPersistenceException;
import com.mycompany.flooringmastery.service.FlooringOrdersTaxService;
import com.mycompany.flooringmastery.service.FlooringOrdersTaxServiceImpl;
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
public class TaxesServiceTest {

    FlooringOrdersTaxService taxservice;
    FlooringMasteryTaxDao taxdao = new FlooringMasteryTaxDaoFileImpl();
    public TaxesServiceTest() {
        this.taxservice = new FlooringOrdersTaxServiceImpl(taxdao);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws FlooringMasteryPersistenceException {
        List<Taxes> taxList = taxservice.getAllTaxes();
        taxList.clear();
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void testCreateTax() {
        //Arrange
        Taxes tax1 = new Taxes();
        tax1.setTaxRate(new BigDecimal(".065"));
        tax1.setState("OH");

        //Act
        Taxes result = new Taxes();
        try {
            result = taxservice.createTax(tax1);
        } catch (FlooringMasteryPersistenceException e) {
            fail(e.getMessage());
        }
        //Assert
        try {
            assertNotNull(result);

            assertTrue(result.getState().equals("OH"));

            Taxes expected = taxservice.getTax("OH");
            assertNotNull(expected);
        } catch (FlooringMasteryPersistenceException e) {
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testGetAllTaxes() {
        List<Taxes> taxList = new ArrayList<>();
        
        //Arrange
        Taxes tax1 = new Taxes();
        tax1.setTaxRate(new BigDecimal(".065"));
        tax1.setState("OH");

        Taxes tax2 = new Taxes();
        tax2.setTaxRate(new BigDecimal(".08"));
        tax2.setState("TX");

        //Act
        try{
        taxservice.createTax(tax1);
        taxservice.createTax(tax2);
        taxList = taxservice.getAllTaxes();
        } catch (FlooringMasteryPersistenceException e){
            fail(e.getMessage());
        }
        
        //Assert
            assertNotNull(taxList);
            
            assertTrue(taxList.size() == 2);
        
    }
    
    @Test
    public void testGetTax() {
        //Arrange
        Taxes tax1 = new Taxes();
        tax1.setTaxRate(new BigDecimal(".065"));
        tax1.setState("OH");

        //Act
        Taxes result = new Taxes();
        try{
            taxservice.createTax(tax1);
            result = taxservice.getTax("OH");
        } catch (FlooringMasteryPersistenceException e){
            fail(e.getMessage());
        }
        //Assert
        assertNotNull(result);
        
        assertTrue(result.getState().equals("OH"));
    }
    
    @Test
    public void testRemoveTax() {
        Taxes result = new Taxes();
        List<Taxes> taxList = new ArrayList<>();
        
        //Arrange
        Taxes tax1 = new Taxes();
        tax1.setTaxRate(new BigDecimal(".065"));
        tax1.setState("OH");

        Taxes tax2 = new Taxes();
        tax2.setTaxRate(new BigDecimal(".08"));
        tax2.setState("TX");
        
        //Act
        try{
            taxservice.createTax(tax1);
            taxservice.createTax(tax2);
            taxservice.removeTax("TX");
            result = taxservice.getTax("TX");
            taxList = taxservice.getAllTaxes(); 
        } catch (FlooringMasteryPersistenceException e){
            fail(e.getMessage());
        }
        //Assert
        assertNull(result);
        assertTrue(taxList.size() == 1);
        
        //Act
        try{
            taxservice.removeTax("OH");
            result = taxservice.getTax("OH");
            taxList = taxservice.getAllTaxes();
        } catch (FlooringMasteryPersistenceException e){
            fail(e.getMessage());
        }
        
        //Assert
        assertNull(result);
        assertTrue(taxList.isEmpty());
    }
    
    @Test
    public void testUpdateTax() {
        Taxes result = new Taxes();
        List<Taxes> taxList = new ArrayList<>();
        //Arrange
        Taxes tax1 = new Taxes();
        tax1.setTaxRate(new BigDecimal(".065"));
        tax1.setState("OH");

        Taxes tax2 = new Taxes();
        tax2.setTaxRate(new BigDecimal(".08"));
        tax2.setState("OH");

        //Act
        try{
            taxservice.createTax(tax1);
            taxservice.updateTax(tax2);
            result = taxservice.getTax("OH");
            taxList = taxservice.getAllTaxes();
        } catch (FlooringMasteryPersistenceException e){
            fail(e.getMessage());
        }
        //Assert
        BigDecimal taxRate = result.getTaxRate();
        assertTrue(taxRate.equals(new BigDecimal(".08")));
        
       assertTrue(taxList.size() == 1);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
//    Taxes createTax(Taxes tax);
//    
//    List<Taxes> getAllTaxes();
//    
//    Taxes getTax(String state);
//    
//    Taxes removeTax(String state);
//    
//    Taxes editTax(String state);
}

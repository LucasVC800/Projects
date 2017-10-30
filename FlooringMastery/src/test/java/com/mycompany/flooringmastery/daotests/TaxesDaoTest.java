/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.daotests;

import com.mycompany.flooringmastery.dao.FlooringMasteryTaxDao;
import com.mycompany.flooringmastery.dao.FlooringMasteryTaxDaoFileImpl;
import com.mycompany.flooringmastery.dto.Taxes;
import com.mycompany.flooringmastery.exceptions.FlooringMasteryPersistenceException;
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
public class TaxesDaoTest {

    FlooringMasteryTaxDao taxdao;

    public TaxesDaoTest() {
        this.taxdao = new FlooringMasteryTaxDaoFileImpl();
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
    public void testCreateTax() {
        //Arrange
        Taxes tax1 = new Taxes();
        tax1.setTaxRate(new BigDecimal(".065"));
        tax1.setState("OH");

        //Act
        Taxes result = new Taxes();
        try {
            result = taxdao.createTax(tax1);
        } catch (FlooringMasteryPersistenceException e) {
            fail(e.getMessage());
        }
        //Assert
        try {
            assertNotNull(result);

            assertTrue(result.getState().equals("OH"));

            Taxes expected = taxdao.getTax("OH");
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
        try {
            taxdao.createTax(tax1);
            taxdao.createTax(tax2);
            taxList = taxdao.getAllTaxes();
        } catch (FlooringMasteryPersistenceException e) {
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
        try {
            taxdao.createTax(tax1);
            result = taxdao.getTax("OH");
        } catch (FlooringMasteryPersistenceException e) {
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
        try {
            taxdao.createTax(tax1);
            taxdao.createTax(tax2);
            taxdao.removeTax("TX");
            result = taxdao.getTax("TX");
            taxList = taxdao.getAllTaxes();
        } catch (FlooringMasteryPersistenceException e) {
            fail(e.getMessage());
        }
        //Assert
        assertNull(result);
        assertTrue(taxList.size() == 1);

        //Act
        try {
            taxdao.removeTax("OH");
            result = taxdao.getTax("OH");
            taxList = taxdao.getAllTaxes();
        } catch (FlooringMasteryPersistenceException e) {
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
        try {
            taxdao.createTax(tax1);
            taxdao.updateTax(tax2);
            result = taxdao.getTax("OH");
            taxList = taxdao.getAllTaxes();
        } catch (FlooringMasteryPersistenceException e) {
            fail(e.getMessage());
        }
        //Assert
        BigDecimal taxRate = result.getTaxRate();
        assertTrue(taxRate.equals(new BigDecimal(".08")));

        assertTrue(taxList.size() == 1);

        // TODO add test methods here.
        // The methods must be annotated with annotation @Test. For example:
        //
        // @Test
        // public void hello() {}
    }
}

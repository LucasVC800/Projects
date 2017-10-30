/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.dto.Taxes;
import com.mycompany.flooringmastery.exceptions.FlooringMasteryPersistenceException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Lucas
 */
public interface FlooringOrdersService {
    
    List<Order> getAllOrders(LocalDate date)  throws FlooringMasteryPersistenceException;
    
    List<Order> getEveryOrder() throws FlooringMasteryPersistenceException;
    
    Order addOrder(Order order)  throws FlooringMasteryPersistenceException;
    
    Order removeOrder(Integer orderNum) throws FlooringMasteryPersistenceException;
    
    Order editOrder(Order newOrder) throws FlooringMasteryPersistenceException;
    
    Order getOrder(Integer orderNum)  throws FlooringMasteryPersistenceException;
    
    BigDecimal calculateMaterialCost(Product product, Double area);
    
    BigDecimal calculateLaborCost(Product product, Double area);
    
    BigDecimal calculateTaxCost(Taxes tax, BigDecimal materialCost, BigDecimal laborCost);
    
    BigDecimal calculateTotalCost(BigDecimal materialCost, BigDecimal laborCost, BigDecimal taxCost);
    
    void setMode(Integer i);
    
    Integer getOrderNumber() throws FlooringMasteryPersistenceException;
    
    
    
}

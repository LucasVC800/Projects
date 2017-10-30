/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.exceptions.FlooringMasteryPersistenceException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Lucas
 */
public interface FlooringMasteryDao {
    
    List<Order> getAllOrders() throws FlooringMasteryPersistenceException;
    
    public List<Order> getOrdersByDate(LocalDate a) throws FlooringMasteryPersistenceException;
    
    Order addOrder (Order order)  throws FlooringMasteryPersistenceException;
    
    Order getOrder (Integer orderNum);
    
    Order removeOrder (Integer orderNum)  throws FlooringMasteryPersistenceException;
    
    Order editOrder (Order neworder) throws FlooringMasteryPersistenceException;

    Integer createOrderNum()  throws FlooringMasteryPersistenceException;
    
}

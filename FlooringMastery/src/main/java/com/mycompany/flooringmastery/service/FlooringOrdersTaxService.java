/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dto.Taxes;
import com.mycompany.flooringmastery.exceptions.FlooringMasteryPersistenceException;
import java.util.List;

/**
 *
 * @author Lucas
 */
public interface FlooringOrdersTaxService {
    
    Taxes createTax(Taxes tax) throws FlooringMasteryPersistenceException;
    
    List<Taxes> getAllTaxes() throws FlooringMasteryPersistenceException;
    
    Taxes getTax(String state) throws FlooringMasteryPersistenceException;
    
    Taxes removeTax(String state) throws FlooringMasteryPersistenceException;
    
    Taxes updateTax(Taxes tax) throws FlooringMasteryPersistenceException;
    
}

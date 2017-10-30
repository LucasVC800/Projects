/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.exceptions.FlooringMasteryPersistenceException;
import java.util.List;

/**
 *
 * @author Lucas
 */
public interface FlooringOrdersProductService {
    
    Product createProduct(Product product) throws FlooringMasteryPersistenceException;
    
    List<Product> getAllProducts() throws FlooringMasteryPersistenceException;
    
    Product getProduct(String productType) throws FlooringMasteryPersistenceException;
    
    Product removeProduct (String productType) throws FlooringMasteryPersistenceException;
    
    Product updateProduct (Product product) throws FlooringMasteryPersistenceException;
    
}

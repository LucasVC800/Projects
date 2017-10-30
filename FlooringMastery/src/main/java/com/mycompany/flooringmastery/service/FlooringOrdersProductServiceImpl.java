/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.FlooringMasteryProductDao;
import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.exceptions.FlooringMasteryPersistenceException;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class FlooringOrdersProductServiceImpl implements FlooringOrdersProductService {
    
    FlooringMasteryProductDao productDao;
    
    public FlooringOrdersProductServiceImpl(FlooringMasteryProductDao productDao){
        this.productDao = productDao;
    }

    @Override
    public Product createProduct(Product product) throws FlooringMasteryPersistenceException {
        return productDao.createProduct(product);
    }

    @Override
    public List<Product> getAllProducts() throws FlooringMasteryPersistenceException  {
        return productDao.getAllProducts();
    }

    @Override
    public Product getProduct(String productType) throws FlooringMasteryPersistenceException  {
        return productDao.getProduct(productType);
    }

    @Override
    public Product removeProduct(String productType) throws FlooringMasteryPersistenceException  {
        return productDao.removeProduct(productType);
    }

    @Override
    public Product updateProduct(Product product) throws FlooringMasteryPersistenceException  {
        return productDao.updateProduct(product);
    }
    
}

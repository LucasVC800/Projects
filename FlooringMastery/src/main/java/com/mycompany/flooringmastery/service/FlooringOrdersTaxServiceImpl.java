/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.FlooringMasteryTaxDao;
import com.mycompany.flooringmastery.dto.Taxes;
import com.mycompany.flooringmastery.exceptions.FlooringMasteryPersistenceException;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class FlooringOrdersTaxServiceImpl implements FlooringOrdersTaxService {
    FlooringMasteryTaxDao taxDao;
    
    
    public FlooringOrdersTaxServiceImpl(FlooringMasteryTaxDao taxDao){
        this.taxDao = taxDao;
        
    }

    @Override
    public Taxes createTax(Taxes tax) throws FlooringMasteryPersistenceException {
        return taxDao.createTax(tax);
    }

    @Override
    public List<Taxes> getAllTaxes() throws FlooringMasteryPersistenceException {
        return taxDao.getAllTaxes();
    }

    @Override
    public Taxes getTax(String state) throws FlooringMasteryPersistenceException {
        return taxDao.getTax(state);
    }

    @Override
    public Taxes removeTax(String state) throws FlooringMasteryPersistenceException {
        return taxDao.removeTax(state);
    }

    @Override
    public Taxes updateTax(Taxes tax) throws FlooringMasteryPersistenceException {
        return taxDao.updateTax(tax);
    }
    
}

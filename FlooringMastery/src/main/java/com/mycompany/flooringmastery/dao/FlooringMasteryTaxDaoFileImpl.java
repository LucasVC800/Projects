/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.dto.Taxes;
import com.mycompany.flooringmastery.dto.Taxes;
import com.mycompany.flooringmastery.exceptions.FlooringMasteryPersistenceException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Lucas
 */
public class FlooringMasteryTaxDaoFileImpl implements FlooringMasteryTaxDao {
    
    Map<String, Taxes> taxMap = new HashMap();
    String TAX_FILE = "taxes.txt";
    String DELIMITER = ",";

    @Override
    public Taxes createTax(Taxes tax) throws FlooringMasteryPersistenceException{
        taxMap.put(tax.getState(), tax);
        return tax;
    }

    @Override
    public List<Taxes> getAllTaxes() throws FlooringMasteryPersistenceException{
        return new ArrayList(taxMap.values());
    }

    @Override
    public Taxes getTax(String state) throws FlooringMasteryPersistenceException{
        loadTax();
        return taxMap.get(state);
    }

    @Override
    public Taxes removeTax(String state) throws FlooringMasteryPersistenceException{
        return taxMap.remove(state);
    }

    @Override
    public Taxes updateTax(Taxes tax) throws FlooringMasteryPersistenceException{
        taxMap.put(tax.getState(), tax);
        return tax;
    }

    private void loadTax() throws FlooringMasteryPersistenceException {
        Scanner scanner;
        
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(TAX_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException(
                    "-_- Could not load roster data into memory.", e);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Taxes currentTaxes = new Taxes();
            currentTaxes.setState(currentTokens[0]);
            currentTaxes.setTaxRate(new BigDecimal(currentTokens[1]));
            taxMap.put(currentTaxes.getState(), currentTaxes);
        }
        scanner.close();
    }
    
    public void writeTax() throws FlooringMasteryPersistenceException{
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(TAX_FILE));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException(
                    "Could not save student data.", e);
        }
        List<Taxes> taxList = this.getAllTaxes();
        for (Taxes currentTax : taxList) {
                out.println(currentTax.getState() + DELIMITER
                        + currentTax.getTaxRate());
                out.flush();
        }
        out.close();
    }

    
}

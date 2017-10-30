/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.dto.Product;
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
public class FlooringMasteryProductDaoFileImpl implements FlooringMasteryProductDao {
    
    Map<String, Product> productMap = new HashMap();
    String PRODUCT_FILE = "products.txt";
    String DELIMITER = ",";
    
    public FlooringMasteryProductDaoFileImpl(){
    }

    @Override
    public List<Product> getAllProducts() throws FlooringMasteryPersistenceException {
        return new ArrayList(productMap.values());
    }

    @Override
    public Product createProduct(Product product) {
        productMap.put(product.getProductType(), product);
        return product;
    }

    @Override
    public Product getProduct(String productType) throws FlooringMasteryPersistenceException{
        loadProducts();
        return productMap.get(productType);
    }

    @Override
    public Product removeProduct(String productType) throws FlooringMasteryPersistenceException {
        return productMap.remove(productType);
    }

    private void loadProducts() throws FlooringMasteryPersistenceException{
        Scanner scanner;
        
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(PRODUCT_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException(
                    "-_- Could not load roster data into memory.", e);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Product currentProduct = new Product();
            currentProduct.setProductType(currentTokens[0]);
            currentProduct.setLaborCostPerSqFoot(new BigDecimal(currentTokens[1]));
            currentProduct.setMaterialCostPerSqFoot(new BigDecimal(currentTokens[2]));
            productMap.put(currentProduct.getProductType(), currentProduct);
        }
        scanner.close();
    }

    private void writeProducts() throws FlooringMasteryPersistenceException{
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(PRODUCT_FILE));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException(
                    "Could not save student data.", e);
        }
        List<Product> productList = this.getAllProducts();
        for (Product currentProduct : productList) {
                out.println(currentProduct.getProductType() + DELIMITER
                        + currentProduct.getLaborCostPerSqFoot()+ DELIMITER
                        + currentProduct.getMaterialCostPerSqFoot());
                out.flush();
            
        }

        out.close();
    }

    @Override
    public Product updateProduct(Product product) {
        productMap.put(product.getProductType(), product);
        return product;
    }
    
}

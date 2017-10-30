/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.exceptions.FlooringMasteryPersistenceException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Lucas
 */
public class FlooringMasteryTrainingOrderFileImpl implements FlooringMasteryDao {

 LocalDate a;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
    String date;
    String ITEM_FILE;
    String ORDERCOUNTER_FILE = "orderCounter.txt";
    final String DELIMITER = ",";
    public static final String ORDER_FILE_HEADER = "OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,"
            + "LaborCostPerSqFoot,MaterialCost,LaborCost,Tax,Total";

    Integer newCounter = 1;

    private Map<Integer, Order> orderMap = new HashMap();
    
    public FlooringMasteryTrainingOrderFileImpl() throws FlooringMasteryPersistenceException{
        loadOrderNumber();
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate a) throws FlooringMasteryPersistenceException {
        return getAllOrders();
    }

    @Override
    public List<Order> getAllOrders() throws FlooringMasteryPersistenceException {
        return new ArrayList(orderMap.values());
    }

    private void loadOrderNumber() throws FlooringMasteryPersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ORDERCOUNTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException(
                    "-_- Could not load roster data into memory.", e);
        }

        String currentLine;
        String[] currentTokens;
        

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            newCounter = (Integer.parseInt(currentTokens[0]));
        }
        scanner.close();

    }

    public void loadRoster(LocalDate a) throws FlooringMasteryPersistenceException {
        Scanner scanner;
        orderMap.clear();

        date = a.format(formatter);
        ITEM_FILE = "Orders_" + date + ".txt";

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ITEM_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException(
                    "-_- Could not load roster data into memory.", e);
        }

        String currentLine;
        String[] currentTokens;
        
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Order currentOrder = new Order();
            currentOrder.setOrderNumber(Integer.parseInt(currentTokens[0]));
            currentOrder.setCustomerName(currentTokens[1]);
            currentOrder.setState(currentTokens[2]);
            currentOrder.setOrderDate(LocalDate.parse(currentTokens[3]));
            currentOrder.setProductType(currentTokens[4]);
            currentOrder.setArea(Double.parseDouble(currentTokens[5]));
            currentOrder.setMaterialCost(new BigDecimal(currentTokens[6]));
            currentOrder.setLaborCost(new BigDecimal(currentTokens[7]));
            currentOrder.setTaxCost(new BigDecimal(currentTokens[8]));
            currentOrder.setTotalCost(new BigDecimal(currentTokens[9]));

            orderMap.put(currentOrder.getOrderNumber(), currentOrder);
        }
        scanner.close();
    }

    @Override
    public Order addOrder(Order order) throws FlooringMasteryPersistenceException {
        loadOrderNumber();
        Integer num = createOrderNum();
        order.setOrderNumber(num);
        orderMap.put(order.getOrderNumber(), order);
        return order;
    }

    @Override
    public Order getOrder(Integer orderNum) {
        return orderMap.get(orderNum);
    }

    @Override
    public Order removeOrder(Integer orderNum) throws FlooringMasteryPersistenceException {
        Order order = orderMap.get(orderNum);
        orderMap.remove(orderNum);
        return order;
    }

    @Override
    public Order editOrder(Order newOrder) throws FlooringMasteryPersistenceException {
        orderMap.put(newOrder.getOrderNumber(), newOrder);
        return newOrder;

    }

    @Override
    public Integer createOrderNum() throws FlooringMasteryPersistenceException{
        Integer orderNum = newCounter;
        ++newCounter;
        return orderNum;
    }
    
}

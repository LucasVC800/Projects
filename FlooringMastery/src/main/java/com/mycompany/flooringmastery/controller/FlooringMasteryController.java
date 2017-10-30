/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.controller;

import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.exceptions.FlooringMasteryPersistenceException;
import com.mycompany.flooringmastery.service.FlooringOrdersProductService;
import com.mycompany.flooringmastery.ui.FlooringMasteryView;
import com.mycompany.flooringmastery.service.FlooringOrdersService;
import com.mycompany.flooringmastery.service.FlooringOrdersTaxService;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class FlooringMasteryController {

    FlooringMasteryView view;
    FlooringOrdersService ordersservice;
    FlooringOrdersProductService productservice;
    FlooringOrdersTaxService taxesservice;

    public FlooringMasteryController(FlooringMasteryView view, FlooringOrdersService ordersservice, FlooringOrdersProductService productservice, FlooringOrdersTaxService taxservice) {
        this.view = view;
        this.ordersservice = ordersservice;
        this.productservice = productservice;
        this.taxesservice = taxservice;
    }

    public void run()  throws FlooringMasteryPersistenceException{
        boolean playagain = true;
        int userChoice = 0;
        int modeChoice = view.displayModeSelection();
        ordersservice.setMode(modeChoice);
        view.displayLogoBanner();
        while (playagain) {
            try {
                userChoice = view.displayMainMenu();
                
                switch (userChoice) {
                    case 1:
                        displayOrders();
                        break;

                    case 2:
                        addOrder();
                        break;

                    case 3:
                        editOrder();
                        break;

                    case 4:
                        removeOrder();
                        break;

                    case 5:
                        saveCurrentInProcessOrder();
                        break;

                    case 6:
                        view.displayExitBanner();
                        playagain = false;

                    default:
                }

            } catch (FlooringMasteryPersistenceException e) {

            }
        }
    }

    public void displayOrders() throws FlooringMasteryPersistenceException {
        LocalDate date = view.getDateChoice();
        List<Order> orders = ordersservice.getAllOrders(date);
        view.printOrders(orders);
        

    }

    public void addOrder() throws FlooringMasteryPersistenceException {
        Order newOrder = view.getNewOrderInfo();
        view.displayNewOrder(newOrder);
        int choice = view.getCommitChoice();
        if (choice == 1){
        ordersservice.addOrder(newOrder);
        }

    }

    public void editOrder() throws FlooringMasteryPersistenceException{
        LocalDate date = view.getDateChoice();
        List<Order> orders = ordersservice.getAllOrders(date);
        view.printOrders(orders);
        int orderNum = view.getEditChoice();
        Order editOrder = ordersservice.getOrder(orderNum);
        Order newOrder = view.displayEditMenu(editOrder, orderNum);
        ordersservice.editOrder(newOrder);

    }

    public void removeOrder() throws FlooringMasteryPersistenceException {
        LocalDate date = view.getDateChoice();
        List<Order> orders = ordersservice.getAllOrders(date);
        view.printOrders(orders);
        int deleteChoice = view.getDeleteChoice();
        ordersservice.removeOrder(deleteChoice);

    }

    public void saveCurrentInProcessOrder() {

    }
    
    public Integer getOrderNumber(){
        return ordersservice.getOrderNumber();
    }

}

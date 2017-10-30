/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.ui;

import com.mycompany.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class FlooringMasteryView {

    UserIO myIO = new UserIOImplement();

    public FlooringMasteryView(UserIO myIO) {
        this.myIO = myIO;
    }

    public void displayLogoBanner() {
        myIO.print(
                "--------------------------------------------------------------------------------------------------------------------\n"
                + "___________.__                              _____                     __                    ____      .________\n"
                + "\\_   _____/|  |    ____    ____  _______   /     \\  _____     _______/  |_   ____  _______ /_   |     |   ____/\n"
                + " |    __)  |  |   /  _ \\  /  _ \\ \\_  __ \\ /  \\ /  \\ \\__  \\   /  ___/\\   __\\_/ __ \\ \\_  __ \\ |   |     |____  \\ \n"
                + " |     \\   |  |__(  <_> )(  <_> ) |  | \\//    Y    \\ / __ \\_ \\___ \\  |  |  \\  ___/  |  | \\/ |   |     /       \\\n"
                + " \\___  /   |____/ \\____/  \\____/  |__|   \\____|__  /(____  //____  > |__|   \\___  > |__|    |___| /\\ /______  /\n"
                + "     \\/                                          \\/      \\/      \\/             \\/                \\/        \\/ \n"
                + "--------------------------------------------------------------------------------------------------------------------");
    }

    public int displayMainMenu() {
        myIO.print("| 1. Display Orders");
        myIO.print("| 2. Add an Order");
        myIO.print("| 3. Edit an Order");
        myIO.print("| 4. Remove an Order");
        myIO.print("| 5. Save Current Work");
        myIO.print("| 6. Quit");
        return myIO.readInt("Enter an Option: ");

    }

    public void displayAllOrders() {

    }

    public void displayOrder() {

    }

    public void AddOrder() {

    }

    public void displayExitBanner() {
        myIO.print("=== GOOD BYE !!! ===");

    }

    public void displayUnknownCommand() {

    }

    public void displayOrderDoesNotExistBanner() {

    }

    public void displayErrorMessageBanner() {

    }

    public Order getNewOrderInfo() {
        myIO.print("=== ADD NEW ORDER ===");
        String name = myIO.readString("Enter Customer Name: ");
        String state = myIO.readString("Enter Customer State: ");
        LocalDate date = myIO.readLocalDate("Enter Order Date (YYYY-MM-DD: )");
        String type = myIO.readString("Enter Floor Type: ");
        Double area = myIO.readDouble("Enter Floor Area: ");
        Order newOrder = new Order();
        newOrder.setCustomerName(name);
        newOrder.setState(state);
        newOrder.setOrderDate(date);
        newOrder.setProductType(type);
        newOrder.setArea(area);

        return newOrder;
    }

    public Integer displayModeSelection() {
        myIO.print("=== CHOOSE MODE ===");
        return myIO.readInt("1 - Production   2 - Training");

    }

    public LocalDate getDateChoice() {
        return myIO.readLocalDate("Please Enter Date To Display (YYYY-MM-DD)");
    }

    public void printOrders(List<Order> orders) {
        for (Order k : orders) {
            myIO.print("--------------------------------");
            myIO.print("Order #: " + k.getOrderNumber() + " | Name: " + k.getCustomerName() + " |");
            myIO.print("Date: " + k.getOrderDate());
            myIO.print("Floor Type: " + k.getProductType() + " | Area: " + k.getArea() + " Sq. Ft");
            myIO.print("Total Cost: " + k.getTotalCost());
            myIO.print("Material Cost: " + k.getMaterialCost() + " | Labor Cost: " + k.getLaborCost() + " | Tax: " + k.getTaxCost());
            myIO.print("--------------------------------");
        }
        myIO.readString("Press Enter To Continue");
    }

    public int getDeleteChoice() {
        return myIO.readInt("Please Enter ID Of Entry You Wish To Delete:");
    }

    public Order displayEditMenu(Order editOrder, int orderNum) {
        Order newOrder = new Order();
        newOrder.setOrderNumber(orderNum);

        String name = myIO.readString("Enter Customer Name (" + editOrder.getCustomerName()+"): ");
        if (name != null) {
            newOrder.setCustomerName(name);
        } else {
            newOrder.setCustomerName(editOrder.getCustomerName());
        }

        String state = myIO.readString("Enter Customer State (" + editOrder.getState() +"): ");
        if (state != null) {
            newOrder.setState(state);
        } else {
            newOrder.setState(editOrder.getState());
        }

        LocalDate date = myIO.readLocalDate("Enter Order Date (YYYY-MM-DD) ("  + editOrder.getOrderDate() +"): ");
        if (date != null) {
            newOrder.setOrderDate(date);
        } else {
            newOrder.setOrderDate(editOrder.getOrderDate());
        }

        String type = myIO.readString("Enter Floor Type: ");
        if (type != null) {
            newOrder.setProductType(type);
        } else {
            newOrder.setProductType(editOrder.getProductType());
        }

        Double area = myIO.readDouble("Enter Floor Area: ");
        if (area != null) {
            newOrder.setArea(area);
        } else {
            newOrder.setArea(editOrder.getArea());
        }

        return newOrder;
    }

    public int getEditChoice() {
        return myIO.readInt("Please Enter ID Of Order To Edit");
    }

    public String getNewCustomerName() {
        return myIO.readString("Please Enter New Name: ");
    }
    
    public int getCommitChoice(){
        return myIO.readInt("Save Info? 1 - Yes | 2 - No", 1, 2);
    }
    
    public void displayNewOrder(Order order){
            myIO.print("--------------------------------");
            myIO.print("Name: " + order.getCustomerName() + " |");
            myIO.print("Date: " + order.getOrderDate());
            myIO.print("Floor Type: " + order.getProductType() + " | Area: " + order.getArea() + " Sq. Ft");
            myIO.print("--------------------------------");
    }

}

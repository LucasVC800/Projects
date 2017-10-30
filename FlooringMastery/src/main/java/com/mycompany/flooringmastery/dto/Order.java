/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Lucas
 */
public class Order {
    Integer orderNumber;
    String customerName;
    String state;
    BigDecimal taxRate;
    String productType;
    Double area;
    BigDecimal materialCostPerSqFoot;
    BigDecimal materialCost;
    BigDecimal laborCostPerSqFoot;
    BigDecimal laborCost;
    BigDecimal taxCost;
    BigDecimal totalCost;
    LocalDate orderDate;

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public BigDecimal getMaterialCostPerSqFoot() {
        return materialCostPerSqFoot;
    }

    public void setMaterialCostPerSqFoot(BigDecimal materialCostPerSqFoot) {
        this.materialCostPerSqFoot = materialCostPerSqFoot;
    }

    public BigDecimal getLaborCostPerSqFoot() {
        return laborCostPerSqFoot;
    }

    public void setLaborCostPerSqFoot(BigDecimal laborCostPerSqFoot) {
        this.laborCostPerSqFoot = laborCostPerSqFoot;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTaxCost() {
        return taxCost;
    }

    public void setTaxCost(BigDecimal taxCost) {
        this.taxCost = taxCost;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}

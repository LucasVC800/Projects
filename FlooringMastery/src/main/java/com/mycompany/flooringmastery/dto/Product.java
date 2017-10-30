/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author Lucas
 */
public class Product {
    String productType;
    BigDecimal MaterialCostPerSqFoot;
    BigDecimal LaborCostPerSqFoot;
    
    public Product(){
        
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getMaterialCostPerSqFoot() {
        return MaterialCostPerSqFoot;
    }

    public void setMaterialCostPerSqFoot(BigDecimal MaterialCostPerSqFoot) {
        this.MaterialCostPerSqFoot = MaterialCostPerSqFoot;
    }

    public BigDecimal getLaborCostPerSqFoot() {
        return LaborCostPerSqFoot;
    }

    public void setLaborCostPerSqFoot(BigDecimal LaborCostPerSqFoot) {
        this.LaborCostPerSqFoot = LaborCostPerSqFoot;
    }
}

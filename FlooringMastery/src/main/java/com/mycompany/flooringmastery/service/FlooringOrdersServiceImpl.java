/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.FlooringMasteryDao;
import com.mycompany.flooringmastery.dao.FlooringMasteryProductDao;
import com.mycompany.flooringmastery.dao.FlooringMasteryProductionOrderDaoFileImpl;
import com.mycompany.flooringmastery.dao.FlooringMasteryTaxDao;
import com.mycompany.flooringmastery.dao.FlooringMasteryTrainingOrderFileImpl;
import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.dto.Taxes;
import com.mycompany.flooringmastery.exceptions.FlooringMasteryPersistenceException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Lucas
 */
public class FlooringOrdersServiceImpl implements FlooringOrdersService {

    // private ApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    FlooringMasteryDao orderdao;
    FlooringMasteryProductDao productdao;
    FlooringMasteryTaxDao taxdao;

    //Constructor
    public FlooringOrdersServiceImpl(FlooringMasteryDao dao, FlooringMasteryProductDao productDao, FlooringMasteryTaxDao taxDao) {
        this.orderdao = dao;
        this.productdao = productDao;
        this.taxdao = taxDao;
    }

    //Setter
    public void setDao(FlooringMasteryDao dao) {
        this.orderdao = dao;
    }

    //Use integer to set Dao
    @Override
    public void setMode(Integer i) {
        switch (i) {
            case 1:
                setDao(new ClassPathXmlApplicationContext("ApplicationContext.xml").getBean("orderDaoProduction", FlooringMasteryProductionOrderDaoFileImpl.class));
                break;
            case 2:
                setDao(new ClassPathXmlApplicationContext("ApplicationContext.xml").getBean("orderDaoTraining", FlooringMasteryTrainingOrderFileImpl.class));
                break;
            default:
                setDao(new ClassPathXmlApplicationContext("ApplicationContext.xml").getBean("orderDaoProduction", FlooringMasteryProductionOrderDaoFileImpl.class));
        }
    }

    @Override
    public List<Order> getAllOrders(LocalDate date) throws FlooringMasteryPersistenceException {
        return orderdao.getOrdersByDate(date);
    }
    
    @Override
    public List<Order> getEveryOrder() throws FlooringMasteryPersistenceException {
        return orderdao.getAllOrders();
    }

    @Override
    public Order addOrder(Order order) throws FlooringMasteryPersistenceException {
        String productType = order.getProductType();
        Product product = productdao.getProduct(productType);

        String state = order.getState();
        Taxes tax = taxdao.getTax(state);

        BigDecimal newMaterialCost = calculateMaterialCost(product, order.getArea());
        order.setMaterialCost(newMaterialCost.setScale(2, RoundingMode.HALF_UP));

        BigDecimal newLaborCost = calculateMaterialCost(product, order.getArea());
        order.setLaborCost(newLaborCost.setScale(2, RoundingMode.HALF_UP));

        BigDecimal newTaxCost = calculateTaxCost(tax, newMaterialCost, newLaborCost);
        order.setTaxCost(newTaxCost.setScale(2, RoundingMode.HALF_UP));

        BigDecimal newTotalCost = calculateTotalCost(newTaxCost, newMaterialCost, newLaborCost);
        order.setTotalCost(newTotalCost.setScale(2, RoundingMode.HALF_UP));
        
        orderdao.addOrder(order);
        
        return order;
    }

    @Override
    public Order removeOrder(Integer orderNum) throws FlooringMasteryPersistenceException {
        return orderdao.removeOrder(orderNum);
    }

    @Override
    public Order editOrder(Order newOrder) throws FlooringMasteryPersistenceException {
        String productType = newOrder.getProductType();
        Product product = productdao.getProduct(productType);

        String state = newOrder.getState();
        Taxes tax = taxdao.getTax(state);

        BigDecimal newMaterialCost = calculateMaterialCost(product, newOrder.getArea());
        newOrder.setMaterialCost(newMaterialCost);

        BigDecimal newLaborCost = calculateMaterialCost(product, newOrder.getArea());
        newOrder.setLaborCost(newLaborCost);

        BigDecimal newTaxCost = calculateTaxCost(tax, newMaterialCost, newLaborCost);
        newOrder.setTaxCost(newTaxCost);

        BigDecimal newTotalCost = calculateTotalCost(newTaxCost, newMaterialCost, newLaborCost);
        newOrder.setTotalCost(newTotalCost);

        orderdao.editOrder(newOrder);
        return newOrder;
    }

    @Override
    public BigDecimal calculateMaterialCost(Product product, Double area) {
        BigDecimal cost
                = product.getMaterialCostPerSqFoot().multiply(new BigDecimal(area));
        return cost;
    }

    @Override
    public BigDecimal calculateLaborCost(Product product, Double area) {
        BigDecimal cost
                = product.getLaborCostPerSqFoot().multiply(new BigDecimal(area));
        return cost;
    }

    @Override
    public BigDecimal calculateTaxCost(Taxes tax, BigDecimal materialCost, BigDecimal laborCost) {
        BigDecimal preTaxCost
                = materialCost.add(laborCost);
        BigDecimal taxCost = tax.getTaxRate().multiply(preTaxCost);
        return taxCost;
    }

    @Override
    public BigDecimal calculateTotalCost(BigDecimal materialCost, BigDecimal laborCost, BigDecimal taxCost) {
        BigDecimal preTaxCost = materialCost.add(laborCost);
        BigDecimal postTaxCost = preTaxCost.add(taxCost);
        return postTaxCost;
    }

    @Override
    public Integer getOrderNumber() throws FlooringMasteryPersistenceException {
        return orderdao.createOrderNum();
    }

    @Override
    public Order getOrder(Integer orderNum) throws FlooringMasteryPersistenceException {
        return orderdao.getOrder(orderNum);
    }

}

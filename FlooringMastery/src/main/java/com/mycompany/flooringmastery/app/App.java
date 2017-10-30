/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.app;

import com.mycompany.flooringmastery.controller.FlooringMasteryController;
import com.mycompany.flooringmastery.exceptions.FlooringMasteryPersistenceException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Lucas
 */
public class App {

    public static void main(String[] args) throws FlooringMasteryPersistenceException {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        FlooringMasteryController controller 
                = ctx.getBean("controller", FlooringMasteryController.class);
        controller.run();
    }

}

package com.mycompany.flooringmastery.ui;

import java.math.BigDecimal;
import java.time.LocalDate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lucas
 */
public interface UserIO {
    void print(String message);

    Double readDouble(String prompt);

    Double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);
    
    LocalDate readLocalDate(String prompt);

    String readString(String prompt);
    
    public BigDecimal readBigDecimal(String prompt);
}

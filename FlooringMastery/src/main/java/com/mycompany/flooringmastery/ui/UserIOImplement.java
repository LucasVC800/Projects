/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.ui;

/**
 *
 * @author Lucas
 */
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Scanner;

public class UserIOImplement implements UserIO {

    Scanner userInput = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);

    }

    @Override
    public Double readDouble(String prompt) {
        Double num = null;
        print(prompt);
        String numString = userInput.nextLine();
        try {
            num = Double.parseDouble(numString);
        } catch (NumberFormatException e) {

        }
        return num;

    }

    @Override
    public Double readDouble(String prompt, double min, double max) {
        print(prompt);
        String numString = userInput.nextLine();
        Double num = Double.parseDouble(numString);
        while (num < min || num > max) {
            print("ERROR: Number out of range");
            print(prompt);
            numString = userInput.nextLine();
            num = Double.parseDouble(numString);
        }
        return num;
    }

    @Override
    public float readFloat(String prompt) {
        print(prompt);
        String numString = userInput.nextLine();
        float num = Float.parseFloat(numString);
        return num;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        print(prompt);
        String numString = userInput.nextLine();
        float num = Float.parseFloat(numString);
        while (num < min || num > max) {
            print("ERROR: Number out of range");
            print(prompt);
            numString = userInput.nextLine();
            num = Float.parseFloat(numString);
        }
        return num;
    }

    @Override
    public int readInt(String prompt) {
        print(prompt);
        String numString = userInput.nextLine();
        int num = Integer.parseInt(numString);
        return num;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        print(prompt);
        String numString = userInput.nextLine();
        int num = Integer.parseInt(numString);
        while (num < min || num > max) {
            print("Error: Number out of range");
            print(prompt);
            String numSting = userInput.nextLine();
            num = Integer.parseInt(numString);
        }
        return num;
    }

    @Override
    public long readLong(String prompt) {
        print(prompt);
        String numString = userInput.nextLine();
        long num = Long.parseLong(numString);
        return num;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        print(prompt);
        String numString = userInput.nextLine();
        long num = Long.parseLong(numString);
        while (num < min || num > max) {
            print("Error: Number out of range");
            print(prompt);
            numString = userInput.nextLine();
            num = Long.parseLong(numString);
        }
        return num;
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        String response = userInput.nextLine();
        if (response.equals("")) {
            return null;
        } else {
            return response;
        }
    }

    @Override
    public LocalDate readLocalDate(String prompt) {
        LocalDate date = null;
        print(prompt);
        String response = userInput.nextLine();
        try {
            date = LocalDate.parse(response);
        } catch (Exception e) {

        }
        return date;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        BigDecimal bigDecimal = new BigDecimal("0");
        boolean validInput = false;
        while (!validInput) {
            String bigDecimalInput = readString(prompt);
            try {
                Double testDouble = Double.parseDouble(bigDecimalInput);
                bigDecimal = BigDecimal.valueOf(testDouble);
                bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_DOWN);
                validInput = true;
            } catch (NumberFormatException e) {
                print("ERROR: Enter A Valid Dollar Amount. ");
            }
        }
        return bigDecimal;
    }

}

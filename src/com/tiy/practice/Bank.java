package com.tiy.practice;

/**
 * Created by jessicatracy on 8/18/16.
 */
public class Bank {
    private String name;

    public void printInfo() {
        System.out.println("Printing bank info...");
        System.out.println("Name: " + name);
        System.out.println("Accounts in bank:");
        //Add in accounts in bank here... call printInfo() method on all accounts!
        System.out.println("Total in deposits: "); //Use getTotalInDeposits() method here
    }

    // All getter and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

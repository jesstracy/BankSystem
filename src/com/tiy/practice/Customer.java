package com.tiy.practice;

import java.util.ArrayList;

/**
 * Created by jessicatracy on 8/18/16.
 */
public class Customer {
    private String name;
    private int numOfAccounts;
    private ArrayList<BankAccount> customerListOfAccounts;

    public void printInfo() {
        System.out.println("Printing customer info...");
        System.out.println("Name: " + name);
        System.out.println("Number of accounts: " + numOfAccounts);
        System.out.println("Accounts:");
        for (BankAccount account: customerListOfAccounts) {
            account.printInfo();
        }
    }

    //All getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfAccounts() {
        return numOfAccounts;
    }

    public void setNumOfAccounts(int numOfAccounts) {
        this.numOfAccounts = numOfAccounts;
    }

    public ArrayList<BankAccount> getCustomerListOfAccounts() {
        return customerListOfAccounts;
    }

    public void setCustomerListOfAccounts(ArrayList<BankAccount> customerListOfAccounts) {
        this.customerListOfAccounts = customerListOfAccounts;
    }
}

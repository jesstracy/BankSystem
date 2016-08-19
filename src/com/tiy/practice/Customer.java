package com.tiy.practice;

import java.util.ArrayList;

/**
 * Created by jessicatracy on 8/18/16.
 */
public class Customer {
    private String name;
    private ArrayList<BankAccount> customerListOfAccounts = new ArrayList<BankAccount>(); //You have to initialize here or else it will be null!!

    public void printInfo() {
        System.out.println("Printing customer info...");
        System.out.println("Name: " + name);
        System.out.println("Number of accounts: " + getNumAccounts());
        System.out.println("Accounts:");
        for (BankAccount account: customerListOfAccounts) {
            account.printInfo();
        }
    }

    public void addBankAccount(BankAccount account) {
        customerListOfAccounts.add(account);
    }

    public int getNumAccounts() {
        int numAccounts = 0;
        for (BankAccount account : customerListOfAccounts) {
            numAccounts++;
        }
        return numAccounts;
    }

    //All getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<BankAccount> getCustomerListOfAccounts() {
        return customerListOfAccounts;
    }

    public void setCustomerListOfAccounts(ArrayList<BankAccount> customerListOfAccounts) {
        this.customerListOfAccounts = customerListOfAccounts;
    }
}

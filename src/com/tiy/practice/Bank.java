package com.tiy.practice;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jessicatracy on 8/18/16.
 */
public class Bank {
    private String name;
    private HashMap<Customer, ArrayList<BankAccount>> customerAccountHashMap;
    private Customer[] customerList;
    private BankAccount[] bankAccountList;

    public void printInfo() {
        System.out.println("Printing bank info...");
        System.out.println("Name: " + name);
        System.out.println("Accounts in bank:");
        for (Customer person : customerAccountHashMap.keySet()) {
            System.out.println("Customer: " + person.getName());
            for (BankAccount account : person.getCustomerListOfAccounts()) {
                account.printInfo();
            }
        }
        System.out.println("Total in deposits: " + getTotalInDeposits()); //Use getTotalInDeposits() method here
    }

    public double getTotalInDeposits() {
        int counter = 1;
        double totalInDeposits = 0;
        for (ArrayList<BankAccount> accountList: customerAccountHashMap.values()) {
            for (BankAccount account : accountList) {
//                System.out.println("Adding balance " + counter + ": " + account.getBalance());
                totalInDeposits += account.getBalance();
            }
//            System.out.println("Bank Total In Deposits so far: " + totalInDeposits);
        }
        return totalInDeposits;
     }

    // All getter and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Customer, ArrayList<BankAccount>> getCustomerAccountHashMap() {
        return customerAccountHashMap;
    }

    public void setCustomerAccountHashMap(HashMap<Customer, ArrayList<BankAccount>> customerAccountHashMap) {
        this.customerAccountHashMap = customerAccountHashMap;
    }

    public Customer[] getCustomerList() {
        return customerList;
    }

    public void setCustomerList(Customer[] customerList) {
        this.customerList = customerList;
    }

    public BankAccount[] getBankAccountList() {
        return bankAccountList;
    }

    public void setBankAccountList(BankAccount[] bankAccountList) {
        this.bankAccountList = bankAccountList;
    }
}

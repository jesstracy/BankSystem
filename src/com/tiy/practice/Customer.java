package com.tiy.practice;

import java.util.ArrayList;

/**
 * Created by jessicatracy on 8/18/16.
 */
public class Customer {
    private String name;
    private ArrayList<BankAccount> customerListOfAccounts;

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

package com.tiy.practice;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jessicatracy on 8/18/16.
 */
public class Bank {
    private String name;
    private ArrayList<Customer> customerList = new ArrayList<Customer>(); //You have to initialize here or else it will be null!!

    public Bank() {
    }

    public Bank(String name) {
        this.name = name;
    }

    public void printInfo() {
        System.out.println("Printing bank info...");
        System.out.println("Name: " + name);
        System.out.println("Accounts in bank:");
        if (customerList.isEmpty()) {
            System.out.println("No accounts to show.");
        } else {
            for (Customer person : customerList) {
                System.out.println("Customer: " + person.getName());
                for (BankAccount account : person.getCustomerListOfAccounts()) {
                    account.printInfo();
                }
            }
        }
        System.out.println("Total in deposits: " + getTotalInDeposits()); //Use getTotalInDeposits() method here
    }

    public double getTotalInDeposits() {
        int counter = 1;
        double totalInDeposits = 0;
        for (Customer person : customerList) {
            for (BankAccount account : person.getCustomerListOfAccounts()) {
                totalInDeposits += account.getBalance();
            }
        }
        return totalInDeposits;
    }

    public void addCustomer(Customer customer) {
        //If the customer list is not empty, check that the new person is not already on list, and if not add them!
        //TRY setting a boolean instead of adding and then add after if boolean is true.
        boolean isOnList = false;
        if (!customerList.isEmpty()) {
            for (Customer customerPerson : customerList) {
                if (customerPerson.equals(customer)) {
                    isOnList = true;
                }
            }
        }
        if (!isOnList) {
            customerList.add(customer);
        }
    }

    public void addBankAccount(Customer customer, BankAccount account) {
        customer.addBankAccount(account);
    }

    //Just prints entire list of customers to file. Replaces old file.
    public void customerListToFile() {
        try {
            File customerListFile = new File("ListOfCustomers.txt");
            FileWriter customerListWriter = new FileWriter(customerListFile);
            for (Customer person : customerList) {
                customerListWriter.write(person.getName() + ",");
            }
            customerListWriter.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    // All getter and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(ArrayList<Customer> customerList) {
        this.customerList = customerList;
    }
}

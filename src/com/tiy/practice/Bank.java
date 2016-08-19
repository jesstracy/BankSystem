package com.tiy.practice;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by jessicatracy on 8/18/16.
 */
public class Bank {
    private String name;
//    private HashMap<Customer, ArrayList<BankAccount>> customerAccountHashMap;
//    private Customer[] customerList;
//    private BankAccount[] bankAccountList;
    private ArrayList<Customer> customerList = new ArrayList<Customer>(); //You have to initialize here or else it will be null!!

    public Bank() {

    }
    // Constructor for reading in customers from text file
    public Bank (String fileName, String name) {
        this.name = name;
        try {
            File customerFile = new File(fileName);
            Scanner fileScanner = new Scanner(customerFile);
            String customerName;
            String currentLine = fileScanner.nextLine();
            String[] arrayOfCustomerNames = currentLine.split(",");
            int numCustomers = arrayOfCustomerNames.length;
            for (int counter = 0; counter < numCustomers; counter++) {
                Customer newCustomer = new Customer();
                newCustomer.setName(arrayOfCustomerNames[counter]);
                addCustomer(newCustomer);
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

//    public void printInfo() {
//        System.out.println("Printing bank info...");
//        System.out.println("Name: " + name);
//        System.out.println("Accounts in bank:");
//        for (Customer person : customerAccountHashMap.keySet()) {
//            System.out.println("Customer: " + person.getName());
//            for (BankAccount account : person.getCustomerListOfAccounts()) {
//                account.printInfo();
//            }
//        }
//        System.out.println("Total in deposits: " + getTotalInDeposits()); //Use getTotalInDeposits() method here
//    }

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

//    public double getTotalInDeposits() {
//        int counter = 1;
//        double totalInDeposits = 0;
//        for (ArrayList<BankAccount> accountList: customerAccountHashMap.values()) {
//            for (BankAccount account : accountList) {
////                System.out.println("Adding balance " + counter + ": " + account.getBalance());
//                totalInDeposits += account.getBalance();
//            }
////            System.out.println("Bank Total In Deposits so far: " + totalInDeposits);
//        }
//        return totalInDeposits;
//    }

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

    // Don't know if this works yet
    // Ask Dom --> This would mean the only way to add an account would be to add the whole customer first, complete
    // with their list of accounts, to hashmap
    // This is just for new customers?
    // Account for that in addBankAccount method? I would need to add on to the customer's list of accounts in Customer
    // class. But would have to use addBankAccount method to update it in bank class.
//    public void addCustomer(Customer customer) {
//        customerAccountHashMap.put(customer, customer.getCustomerListOfAccounts());
//    }

    // Add anything to this??
    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public void addBankAccount(Customer customer, BankAccount account) {
        customer.addBankAccount(account);
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

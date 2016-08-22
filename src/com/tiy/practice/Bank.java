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
//    private HashMap<Customer, ArrayList<BankAccount>> customerAccountHashMap;
//    private Customer[] customerList;
//    private BankAccount[] bankAccountList;
    private ArrayList<Customer> customerList = new ArrayList<Customer>(); //You have to initialize here or else it will be null!!

    public Bank() {

    }

    public Bank(String name) {
        this.name = name;
    }

    // Constructor for reading in customers from text file
    //Taking out --> Just let the customer constructor add accounts and then add the whole customer to bank in runner.
//    public Bank (String fileName, String name) {
//        this.name = name;
//        try {
//            File customerFile = new File(fileName);
//            Scanner fileScanner = new Scanner(customerFile);
//            String currentLine = fileScanner.nextLine();
//            String[] arrayOfCustomerNames = currentLine.split(",");
//            int numCustomers = arrayOfCustomerNames.length;
//            for (int counter = 0; counter < numCustomers; counter++) {
//                Customer newCustomer = new Customer();
//                newCustomer.setName(arrayOfCustomerNames[counter]);
//                customerList.add(newCustomer);
//            }
//        } catch (Exception exception){
//            exception.printStackTrace();
//        }
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

    //This method doesn't work for some reason. Keep getting exception that says concurrent modification or something
//    public void addCustomer(Customer customer) {
//        //If the customer list is not empty, check that the new person is not already on list, and if not add them!
//        //TRY setting a boolean instead of adding and then add after if boolean is true.
//        if (!customerList.isEmpty()) {
//            for (Customer customerPerson : customerList) {
//                if (!customerPerson.equals(customer)) {
//                    customerList.add(customer);
//                }
//            }
//        } else {
//            customerList.add(customer);
//        }
//    }

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

    //Reads in what used to be in file, stores it in file, and adds TOTAL List of customers.
    //Don't want. This would repeat customers.
    //Can either read in people from list first and make them customers in program (constructor already does this!),
    // then just rewrite list like this every time before close WITHOUT reading part (see below)
    //OR read in people and only write in new people when they are created. Put in creating customers method but use a
    // check to see if they are already on the list. Then use something like this to reprint list and add new customers.
    public void customerListToFile2() {
        // Put read first and store it, then can store into file before adding new stuff
        String currentLine = null;
        try {
            File customerFile = new File("ListOfCustomers.txt");
            Scanner fileScanner = new Scanner(customerFile);
//            if (customerFile.exists()) {
                currentLine = fileScanner.nextLine();
//            }
        } catch (Exception exception){
            exception.printStackTrace();
        }
        try {
            File customerListFile = new File("ListOfCustomers.txt");
            FileWriter customerListWriter = new FileWriter(customerListFile);
            customerListWriter.write(currentLine);
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

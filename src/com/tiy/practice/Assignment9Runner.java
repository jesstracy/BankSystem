package com.tiy.practice;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jessicatracy on 8/18/16.
 */
public class Assignment9Runner {
    public static void main(String[] args) {
        System.out.println("Assignment9Runner running....");
        Assignment9Runner myRunner = new Assignment9Runner();
        myRunner.userInputToFileHardCode();
//        myRunner.testBank();
        myRunner.runProgram();
//        myRunner.createBankCustomersFromFile();
    }

//    public void testBank() {
//        //Need to account for if file doesn't exist? Or maybe just call method first time with nothing in it! Use counter in runner to keep track of if it's the first time run?
//        //First, read in what customers we have from customer file.
//        Bank myBank = new Bank("ListOfCustomers.txt", "Wells Fargo");
//        //Make an array of customers
//        String[] customerNameArray = new String[myBank.getCustomerList().size()];
//        int counter = 0;
//        for (Customer customer : myBank.getCustomerList()) {
//
//            //For each customer, we want to access their list of accounts.
//            customerNameArray[counter] = customer.getName();
//            counter++;
//        }
//        for (int index = 0; index < customerNameArray.length; index++) {
//            //Here we are creating a new customer and putting their accounts in an array of accounts called customerListOfAccounts-- Need to add each one of these to the bank.
//            Customer newCustomer = new Customer(customerNameArray[index] + ".txt", customerNameArray[index]);
//            for (BankAccount account : newCustomer.getCustomerListOfAccounts()) {
//                myBank.addBankAccount(newCustomer, account);
//            }
//        }
//        myBank.printInfo();
//    }

    public void runProgram() {
        //Maybe don't add customers in bank constructor. Just put names in array.
        Bank myBank = new Bank("Wells Fargo");
        //When done -- try putting this part in its own method!! *********************
        ArrayList<String> accountNameList = new ArrayList<String>();
        try {
            File myFile = new File("ListOfCustomers.txt");
            Scanner fileScanner = new Scanner(myFile);
            String currentLine = fileScanner.nextLine();
            String[] splitCurrentLine = currentLine.split(",");
            for (String name : splitCurrentLine) {
                accountNameList.add(name);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        for (String name : accountNameList) {
            Customer myCustomer = new Customer(name + ".txt", name);
            //If this gives problems, use below!!
            myBank.addCustomer(myCustomer);
//            myBank.getCustomerList().add(myCustomer);
        }
        //*****************************************************************************
        myBank.printInfo();
        System.out.println();

        Scanner myScanner = new Scanner(System.in);
        System.out.print("Welcome to the bank. What is your first name? ");
        String userName = myScanner.nextLine();
        Customer myCustomer = new Customer(userName);
        //Still have to check if already a customer here!!
        boolean onList = false;
        for (Customer people : myBank.getCustomerList()) {
            //If their name is already on the list...
            if (myCustomer.getName().equalsIgnoreCase(people.getName())) {
                onList = true;
            }
        }
        if (!onList) {
            //Add customer to bank with accounts!
            System.out.println("That user is not on list! Need to add customer and accounts.");
        } else {
            System.out.println("That user is on the list already. Display options");
        }
        //Display menu



    }

    public void userInputToFileHardCode() {
        FileWriter listOfCustomersWriter = null;
        try {
            File listOfCustomersFile = new File("listOfCustomers.txt");
            listOfCustomersWriter = new FileWriter(listOfCustomersFile);
            listOfCustomersWriter.write("Jessica,");
        } catch (Exception exception) {
            System.out.println("Exception caught...");
            exception.printStackTrace();
        } finally {
            if (listOfCustomersWriter != null) { // At first I had if it equals null, so it skipped right over this
                                                 // and never closed, which is why it did not print anything bc it
                                                 // thought I was going to write more. But listOfCustomersWriter is not
                                                 // null bc I used it to write something (?)
                try {
                    listOfCustomersWriter.close();
                } catch (Exception ex) {
                    System.out.println("Exception caught...");
                    ex.printStackTrace();
                }
            }
        }

/*        FileWriter customerAccountListWriter = null;
        try {
            File customerAccountList = new File("jessicaAccounts.txt");
            customerAccountListWriter = new FileWriter(customerAccountList);
            customerAccountListWriter.write("account.name=Checking\naccount.balance=100.00\naccount.name=Savings\naccount.balance=200.00");
        } catch (Exception exception) {
            System.out.println("Exception caught...");
            exception.printStackTrace();
        } finally {
            if (customerAccountListWriter != null) {
                try {
                    customerAccountListWriter.close();
                } catch (Exception ex) {
                    System.out.println("Exception caught...");
                    ex.printStackTrace();
                }
            }
        }
 */
    }

    public void createBankCustomersFromFile() {
//        Bank myBank = new Bank("listOfCustomers.txt", "Wells Fargo");
//        myBank.printInfo();
        Bank myBank = new Bank();
        myBank.setName("Wells Fargo");

        Customer myCustomer = new Customer("jessicaAccounts.txt", "Jessica");
//        myCustomer.printInfo();

        myBank.addCustomer(myCustomer);
        myBank.printInfo();
    }
}

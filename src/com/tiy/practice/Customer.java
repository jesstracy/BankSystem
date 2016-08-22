package com.tiy.practice;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jessicatracy on 8/18/16.
 */
public class Customer {
    private String name;
    private ArrayList<BankAccount> customerListOfAccounts = new ArrayList<BankAccount>(); //You have to initialize here or else it will be null!!

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public Customer(String fileName, String name) {
        boolean threadsKeepRunning = true;
        this.name = name;
        try {
            File customerAccountFile = new File(fileName);
            Scanner fileScanner = new Scanner(customerAccountFile);
            BankAccount myAccount = null;
            while (fileScanner.hasNext()) {
                String currentLine = fileScanner.nextLine();
                if (currentLine.startsWith("account.name")) {
                    myAccount = new BankAccount();
                    String[] myArray = currentLine.split("=");
                    myAccount.setName(myArray[1]);
                } else if (currentLine.startsWith("account.balance")) {
                    String[] myArray = currentLine.split("=");
                    myAccount.setBalance(Double.valueOf(myArray[1]));
                    if (myAccount != null) {
//                        this.addBankAccount(myAccount);
                        customerListOfAccounts.add(myAccount);
            // $$$$$$$$$$$$$$$$ Thread part that is not working $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
//                        if (myAccount.getName().equals("Savings")) {
//                            // THIS CAST NOT WORKING!!!
//                            SavingsAccount myAccountS = (SavingsAccount)myAccount;
//                            Thread savingsThread = new Thread(myAccountS);
//                            savingsThread.start();
//                        }
//
//                        if (myAccount.getName().equals("Retirement")) {
//                            RetirementAccount myAccountR = (RetirementAccount)myAccount;
//                            Thread retirementThread = new Thread(myAccountR);
//                            retirementThread.start();
//                        }
            // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

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

    // This overwrites whole file and prints all accounts customer has. Fine as long as I read in all
    // existing accounts in constructor as soon as I start.
    public void customerListOfAccountsToFile() {
        try {
            File customerListOfAccountsFile = new File(name + ".txt");
            FileWriter customerListOfAccountsWriter = new FileWriter(customerListOfAccountsFile);
            for (BankAccount account : customerListOfAccounts) {
                customerListOfAccountsWriter.write("account.name=" + account.getName() + "\n");
                customerListOfAccountsWriter.write("account.balance=" + account.getBalance() + "\n");
            }
            customerListOfAccountsWriter.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void customerListOfAccountsToFile2() {
        // Put read first and store it, then can store into file before adding new stuff
        String holdCurrentLine = "";
        try {
            File customerAccountFile = new File(name + ".txt");
            Scanner fileScanner = new Scanner(customerAccountFile);
            while (fileScanner.hasNext()) {
                holdCurrentLine += fileScanner.nextLine() + "\n";
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        try {
            File customerListOfAccountsFile = new File(name + ".txt");
            FileWriter customerListOfAccountsWriter = new FileWriter(customerListOfAccountsFile);
            customerListOfAccountsWriter.write(holdCurrentLine);
            for (BankAccount account : customerListOfAccounts) {
                customerListOfAccountsWriter.write("account.name=" + account.getName() + "\n");
                customerListOfAccountsWriter.write("account.balance=" + account.getBalance() + "\n");
            }
            customerListOfAccountsWriter.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
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

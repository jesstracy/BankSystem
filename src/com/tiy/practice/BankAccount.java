package com.tiy.practice;

import java.io.File;
import java.util.Scanner;

/**
 * Created by jessicatracy on 8/18/16.
 */
public class BankAccount {
    private String name;
    private double balance;

//    public BankAccount() {
//    }
//
//    public BankAccount(String fileName) {
//        try {
//            File customerAccountFile = new File(fileName);
//            Scanner fileScanner = new Scanner(customerAccountFile);
//            while (fileScanner.hasNext()) {
//                String currentLine = fileScanner.nextLine();
//                if (currentLine.startsWith("account.name")) {
//                    String[] myArray = currentLine.split("=");
//                    this.name = myArray[1];
//                } else if (currentLine.startsWith("account.balance")) {
//                    String[] myArray = currentLine.split("=");
//                    this.balance = Double.valueOf(myArray[1]);
//                }
//            }
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//    }

    public double deposit(double amountToDeposit) {
        balance += amountToDeposit;
        return balance;
    }

    public double withdraw(double amountToWithdraw) {
        balance -= amountToWithdraw;
        return balance;
    }

    public double transfer(double amountToTransfer, BankAccount accountToTransferTo) {
        accountToTransferTo.balance += amountToTransfer;
        balance -= amountToTransfer;
        return balance;
    }

    public void printInfo() {
        System.out.println("Printing bank account info...");
        System.out.println("\tName: " + name);
        System.out.println("\tBalance: " + balance);
    }

    //All getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
